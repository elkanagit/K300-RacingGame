package Client;

import BackandForth.Message;
import Client.Backend.BackAndForth;
import Client.Backend.KeyInput;
import Client.Backend.keyLogic;
import Client.GUI.Window;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class Main {

    private keyLogic keyLogic;
    private Window window;

    private Main() throws IOException {
        keyLogic = new keyLogic(this);
        KeyListener listener = new KeyInput(keyLogic);
        window = new Window(this, listener);
        new Thread(keyLogic).start();
//        Socket socket;
//        ObjectInputStream inputStream;
//        ObjectOutputStream outputStream;
//        socket = new Socket(Message.IP,Message.PORT);
//        inputStream = new ObjectInputStream(socket.getInputStream());
//        outputStream = new ObjectOutputStream(socket.getOutputStream());
//        new Thread(new BackAndForth(this, keyLogic, outputStream, inputStream)).start();
        run();
    }

    private void run() {
        while (true) {
            setX(keyLogic.getX());
            System.out.println("");
            setY(keyLogic.getY());
            setDirection(keyLogic.getDirection());
        }
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public void setBackendX(double x) {
        keyLogic.setX(x);
    }

    public void setBackendY(double y) {
        keyLogic.setY(y);
    }

    public void repaint() {
        window.repaint();
    }

    public void setX(double x) {
        window.setPlayerX(x);
    }

    public void setY(double y) {
        window.setPlayerY(y);
    }

    public void setDirection(double direction) {
        window.setAngle(direction);
    }
}
