package br.com.devmedia.jms.producer;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.devmedia.jms.bean.User;

public class JMSProducer {

	public void queueProducer(User user) {
		
        QueueConnectionFactory queueConnectionFactory;
        QueueConnection queueConnection = null;
        QueueSession queueSession;
        Queue queue;
        QueueSender queueSender;
  
        // Aqui serão criadas as propriedades para o serviço JNDI.
        Properties properties = new Properties();
        //propriedade que define qual classe implementa o servidor JNDI.
        properties.put(Context.INITIAL_CONTEXT_FACTORY, 
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        //propriedade que define a URL de conexão com a fila no provedor
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        //propriedade que define a fila
        properties.put("queue.testQueue", "testQueue");
        InitialContext iContext = null;
        try {
            //Inicia o contexto com as configurações contidas nas propriedades.
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
            //Cria um objeto QueueSender com o destino específico.
            queueSender = queueSession.createSender(queue);
            //Cria o corpo da mensagem a partir da sessão e do método createObjectMessage().
            ObjectMessage objectMessage = queueSession.createObjectMessage(user);
            //Envia a mensagem em um objetoMessage contento o objeto User.
            queueSender.send(objectMessage);
  
            System.out.println("Mensagem enviada com sucesso!");
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                //Fecha a conexão
                if (queueConnection != null) {
                    queueConnection.close();
                }
                //Fecha o contexto
                if (iContext != null) {
                    iContext.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
  
    public static void main(String[] args) {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setFirstName("Roberto");
        user.setSurname("Atanasio Pires de Lima");
  
        new JMSProducer().queueProducer(user);
    }
}
