package light;

import Comms.ServerSender;
import Comms.CommConstants;

import java.util.HashMap;
import java.util.Map;

public class LightManager {

    String ip;
    int port;
    Map<String, Light> lights;

    public LightManager(String ipak, int portak){
        ip = ipak;
        port = portak;
        lights = new HashMap<>();
    }

    public void createLight(String id, boolean staircase){
        lights.put(id, new Light(ip, port, id, staircase));
    }

    public Light getLight(String name){
        return lights.get(name);
    }

    public void allOn(){
        String msg = "EVERYon";
        send(msg);
    }
    public void allOff(){
        String msg = "EVERYoff";
        send(msg);
    }
    public void allToggle(){
        String msg = "EVERYswitch";
        send(msg);
        for (Light l : lights.values()) {
            l.setState(!l.getState());
        }
    }
    public void customOn(String groupIdentifier){
        String msg = groupIdentifier + "on";
        send(msg);
    }
    public void customOff(String groupIdentifier){
        String msg = groupIdentifier + "off";
        send(msg);
    }
    public void customToggle(String groupIdentifier){
        String msg = groupIdentifier + "switch";
        send(msg);
    }

    public void askState(){
        String data = ServerSender.ask(ip, port, "STATU");

        if (data != null && !data.equals("Error")) {
            data = data.substring(CommConstants.HEADER_LENGTH);
            String datum;
            int charIndex;
            for (int i = 0; i < CommConstants.SEPARATORS.length; i++) {
                if (data.length() != 0) {
                    charIndex = data.indexOf(CommConstants.SEPARATORS[i]);
                    if (charIndex != -1) {
                        datum = data.substring(0, charIndex);
                        data = data.substring(charIndex + 1);
                        asignState(datum);
                    } else {
                        asignState(data);
                        break;
                    }
                }
            }
        }
    }

    private void asignState(String info){
        String id = info.substring(0,5);
        boolean state = zbooleanit(info.substring(5));
        getLight(id).setState(state);
    }

    private boolean zbooleanit(String i){
        return i.equals("1");
    }

    private void send(String msg){
        ServerSender.send(ip, port, msg);
    }
}
