package asw.intro.server.connector;

import asw.intro.service.Service;

import java.net.*;

import java.util.logging.Logger;

/* remote proxy lato server per il servizio */
public class ServiceServerProxy {

    // riferimento al vero servizio che realizza la logica di business
    private Service service;

    /* porta del server */
    private int port;

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.server.connector");

	public ServiceServerProxy(Service service, int port) {
		logger.info("ServiceServerProxy: Created a new Remote Proxy for Service listening on port " + port);
        this.service = service;
        this.port = port;
    }

    public void run() {
		logger.info("Remote Proxy: Server started");

        // porzione di CONNETTORE LATO SERVER:
        // crea la socket su cui ricevere le richieste, quando ne riceve le elabora
        // decodifica la richiesta, invoca il servizio, codifica la risposta
        // e la invia al socket del client
        try {
            /* crea la socket su cui ricevere le richieste */
            DatagramSocket socket = new DatagramSocket(this.port);
            socket.setSoTimeout(0);  // nessun timeout
            byte[] buffer = new byte[1000];
            while (true) {

                /* aspetta un datagramma di richiesta */
                DatagramPacket requestPacket =
                    new DatagramPacket(buffer, buffer.length);
                socket.receive(requestPacket);    // bloccante

                /* estrae l'invocazione dal datagramma di richiesta */
                String request = new String( requestPacket.getData(),
                		                     requestPacket.getOffset(),
                		                     requestPacket.getLength() );
        		logger.info("Server Proxy: received request: " + request);

                /* la richiesta ha la forma "operazione#parametro" */
                /* estrae operazione e parametro */
                String op = this.getOp(request);
                String arg = this.getParam(request);
                /* effettua l'invocazione del servizio e ottiene il risultato */
                String result = this.executeOperation(op, arg);
        		logger.info("Server Proxy: sending reply: " + result);

                /* crea il datagramma di risposta */
                /* la risposta ha la forma "risultato" */
                byte[] replyMessage = result.getBytes();
                DatagramPacket replyPacket =
                    new DatagramPacket( replyMessage, replyMessage.length,
                                        requestPacket.getAddress(),
                                        requestPacket.getPort() );

                /* invia il datagramma di risposta */
                socket.send(replyPacket);    // non bloccante
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }

    /* estrae l'operazione dalla richiesta */
    private String getOp(String request) {
        /* la richiesta ha la forma "operazione#parametro" */
        int sep = request.indexOf("#");
        String op = request.substring(0,sep);
        return op;
    }

    /* estrae il parametro dalla richiesta */
    private String getParam(String request) {
        /* la richiesta ha la forma "operazione#parametro" */
        int sep = request.indexOf("#");
        String arg = request.substring(sep+1);
        return arg;
    }

    /* gestisce la richiesta del servizio corretto al servente */
    private String executeOperation(String op, String arg) {
        String reply = null;

        if ( op.equals("alpha") ) {
    		logger.info("Server Proxy: calling alpha(" + arg + ")");

            // invoca il servizio (in realta' invoca il metodo in ServiceImpl)
            reply = this.service.alpha(arg);
    		logger.info("Server Proxy: alpha(" + arg + ") ==> " + reply);
        }

        return reply;
    }

}

