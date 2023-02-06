package light;

import Comms.ServerSender;
import Comms.CommConstants;

public class Light {
    String ip;
    int port;
    String id;
    boolean state;
    boolean staircaseSwitch;

    public Light(String ipak, int portak, String idak, boolean staircaseak){
        ip = ipak;
        port = portak;
        id = idak;
        staircaseSwitch = staircaseak;
        state = false;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean newState) {
        state = newState;
    }

    public void on(){
        if (!state) {
            String msg = id + "on";
            send(msg);
            state = true;
        }
    }

    public void off(){
        if (state) {
            String msg = id + "off";
            send(msg);
            state = false;
        }
    }

    public void toggle(){
        String msg = id + "switch";
        send(msg);
        state = !state;
    }

    public void askState(){
        String msg = "STATUsak";
        String stav = ServerSender.ask(ip, port, msg);
        if (!staircaseSwitch && !"Error".equals(stav)) {
            stav = stav.substring(CommConstants.HEADER_LENGTH);
            state = !stav.equals("0");
        }
    }

    private void send(String message){
        ServerSender.send(ip, port, message);
    }
}
