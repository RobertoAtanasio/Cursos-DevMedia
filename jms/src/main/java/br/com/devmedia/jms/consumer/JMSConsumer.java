package br.com.devmedia.jms.consumer;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.devmedia.jms.bean.User;

public class JMSConsumer implements MessageListener {
	
	public void queueConsumer() {
        QueueConnectionFactory queueConnectionFactory;
        QueueConnection queueConnection = null;
        QueueSession queueSession;
        Queue queue;
        QueueReceiver queueReceiver;
  
        //Aqui serão criadas as propriedades para o serviço JNDI.
        Properties properties = new Properties();
        //Propriedade que define qual classe implementa o servidor JNDI.
        properties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        //Propriedade que define a URL de conexão com a fila no provedor.
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        //Propriedade que define a fila no contexto.
        properties.put("queue.testQueue", "testQueue");
        InitialContext iContext = null;
        try {
            //Inicia o contexto com as configurações das propriedades.
            iContext = new InitialContext(properties);
            //Busca pelo Connection Factory apropriado para criar a fábrica de conexão.
            queueConnectionFactory = (QueueConnectionFactory) iContext.lookup("ConnectionFactory");
            //Cria uma conexão a partir da fábrica de conexões.
            queueConnection = queueConnectionFactory.createQueueConnection();
            //Inicia a conexão.
            queueConnection.start();
            //Cria uma sessão a partir da conexão.
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            //Busca pelo destino queue apropriado.
            queue = (Queue) iContext.lookup("testQueue");
            //Cria um objeto QueueReceiver com o destino específico.
            queueReceiver = queueSession.createReceiver(queue);
            //Inicia o ouvinte que irá capturar as mensagem no método onMessage().
            queueReceiver.setMessageListener(this);
  
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
        new JMSConsumer().queueConsumer();
    }
}
