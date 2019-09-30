package br.com.devmedia.jms.consumer;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.devmedia.jms.bean.User;

public class JMSConsumer2 implements MessageListener {
	
	public void topicConsumer() {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Topic topic;
        TopicSubscriber consumer;
  
        //Aqui serão criadas as propriedades para o serviço JNDI.
        Properties properties = new Properties();
        //Propriedade que define qual classe implementa o servidor JNDI.
        properties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        //Propriedade que define a URL de conexão com a fila no provedor.
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        //Propriedade que define a fila no contexto.
        properties.put("topic.testTopic", "testTopic");
        InitialContext iContext = null;
        try {
            //Inicia o contexto com as configurações das propriedades.
            iContext = new InitialContext(properties);
            //Busca pelo Connection Factory apropriado para criar a fábrica de conexão.
            connectionFactory = (ConnectionFactory) iContext.lookup("ConnectionFactory");
            //Cria uma conexão a partir da fábrica de conexões.
            connection = connectionFactory.createConnection();
            //Inicia a conexão.
            connection.setClientID("DevMediaID");
            connection.start();
            //Cria uma sessão a partir da conexão.
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Busca pelo destino queue apropriado.
            topic = (Topic) iContext.lookup("testTopic");
            //Cria um objeto QueueReceiver com o destino específico.
            consumer = session.createDurableSubscriber(topic, "DevMedia");
            consumer.receive();
  
            System.out.println("Ouvinte ativado!");
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (iContext != null) {
                    iContext.close();
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
  
    public void onMessage(Message message) {
        try {
            //Testa se a mensagem é realmente do tipo ObjectMessage.
            if (message instanceof ObjectMessage) {
                //Transforma o objeto message em objectMessage.
                ObjectMessage objectMessage = (ObjectMessage) message;
                //Captura pelo objectMessage o objeto User por meio do método getObject().
                User user = (User) objectMessage.getObject();
                //Imprime no console o objeto user.
                System.out.println(user.toString());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
  
    public static void main(String[] args) {
        new JMSConsumer2().topicConsumer();
    }
}
