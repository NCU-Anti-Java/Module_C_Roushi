package AntiJava;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * UDP Client endpoint of prototype homework
 *
 * Created by fntsr on 2015/11/5.
 */
public class ProtoytypeClient
{
    private static final int ConnectionCount = 1;

    public static void main(String[] args) throws Exception
    {
        String[] IPAddresses = new String[ConnectionCount];
        BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please Enter " + ConnectionCount + " IP address");
        for (int i = 0; i < ConnectionCount; i++)
        {
            IPAddresses[i] = keyIn.readLine();
        }

        System.out.println("Start Client");
        UDPClient client = new UDPClient(IPAddresses);
        client.sendListener = new SenderListener(client);
        client.frequency = 5;
        client.start();
        client.run();
    }

    private static class SenderListener implements ISenderListener
    {
        private int xPos;
        private int yPos;
        private int counter;
        private UDPClient client;

        public SenderListener(UDPClient client) throws Exception
        {
            xPos = 0;
            yPos = 0;
            counter = 0;
            this.client = client;
        }

        @Override
        public void onCycle() throws Exception
        {
            counter += 2;
            String sentence = xPos + " " + yPos;

            if (counter == 20)
            {
                xPos = xPos > 99 ? 0 : xPos + 1;
                yPos = yPos > 99 ? 0 : yPos + 1;
                counter = 0;
            }
            client.sendData(sentence);
        }
    }
}