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
  
        // Aqui ser�o criadas as propriedades para o servi�o JNDI.
        Properties properties = new Properties();
        //propriedade que define qual classe implementa o servidor JNDI.
        properties.put(Context.INITIAL_CONTEXT_FACTORY, 
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        //propriedade que define a URL de conex�o com a fila no provedor
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        //propriedade que define a fila
        properties.put("queue.testQueue", "testQueue");
        InitialContext iContext = null;
        try {
            //Inicia o contexto com as configura��es contidas nas propriedades.
            iContext = new InitialContext(properties);
            //Busca pelo Connection Factory apropriado para criar a f�brica de conex�o.
            queueConnectionFactory = (QueueConnectionFactory) iContext.lookup("ConnectionFactory");
            //Cria uma conex�o a partir da f�brica de conex�es.
            queueConnection = queueConnectionFactory.createQueueConnection();
            //Inicia a conex�o.
            queueConnection.start();
            //Cria uma sess�o a partir da conex�o.
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            //Busca pelo destino queue apropriado.
            queue = (Queue) iContext.lookup("testQueue");
            //Cria um objeto QueueSender com o destino espec�fico.
            queueSender = queueSession.createSender(queue);
            //Cria o corpo da mensagem a partir da sess�o e do m�todo createObjectMessage().
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
                //Fecha a conex�o
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
