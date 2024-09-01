package HW6;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

class Computer {
    int index;
    int clearance;
    boolean linked = false;
}


class Connection   {
    int computer1;
    int computer2;
    int cost;

    int compaterTo(Connection other) {
        return this.cost - other.cost;
    }
}

public class NetworkSetupNew {

    private static Map<Integer, Map<Integer, Computer>> HashHashComputer = new HashMap<>();
    private static final Map<Integer,Computer> hmLinkedComputer = new HashMap<>();
    private static final List<Connection> connections = new ArrayList<>();
    private static boolean firstLink =true;
    private static int totalCost = 0;

    private static void readFile(){
            Scanner scanner = new Scanner(System.in);
            int numberOfComputers = scanner.nextInt();
            int m = scanner.nextInt();
            for (int i = 0; i < numberOfComputers; i++) {
                Computer computer = new Computer();
                computer.index = i;
                computer.clearance = scanner.nextInt();
                HashHashComputer.putIfAbsent(computer.clearance, new HashMap<>());
                HashHashComputer.get(computer.clearance).put(computer.index, computer);

            }
        for (int i = 0; i < m; i++) {
            Connection connection = new Connection();
            connection.computer1 = scanner.nextInt();
            connection.computer2 = scanner.nextInt();
            connection.cost = scanner.nextInt();
            connections.add(connection);
        }

        }


    public static boolean linkSameClearance( HashMap hm){
        boolean allLinked=false;
        int linkedComputerCount =0;

        for(Connection connection: connections){
            if(firstLink) {

                if (hm.get(connection.computer1) != null && hm.get(connection.computer2) != null) {
                    ((Computer) (hm.get(connection.computer1))).linked = true;
                    ((Computer) (hm.get(connection.computer2))).linked = true;
                    hmLinkedComputer.put(connection.computer1, (Computer) hm.get(connection.computer1));
                    hmLinkedComputer.put(connection.computer2, (Computer) hm.get(connection.computer2));
                    totalCost = connection.cost;
                    firstLink = false;
                    System.out.println("Computer " + connection.computer1 + " (" + ((Computer) hm.get(connection.computer1)).clearance + ") is connected to Computer " + connection.computer2 + " (" + ((Computer) hm.get(connection.computer2)).clearance + ") at Cost :"+ connection.cost +" Total Cost is:"+totalCost);
                    hm.remove(connection.computer1);
                    hm.remove(connection.computer2);
                    allLinked=linkSameClearance(  hm);
                    linkedComputerCount = 2;
                    //print linked computers

                }
            }else if (hm.get(connection.computer1) != null && hmLinkedComputer.get(connection.computer2) != null && hmLinkedComputer.get(connection.computer1) == null )  {
                ((Computer) (hm.get(connection.computer1))).linked = true;
                hmLinkedComputer.put(connection.computer1, (Computer) hm.get(connection.computer1));
                totalCost += connection.cost;
                linkedComputerCount++;
                //print linked computers
                System.out.println("Computer " + connection.computer1 + " (" + ((Computer) hm.get(connection.computer1)).clearance + ") is connected to Computer " + connection.computer2 + " (" + ((Computer) hmLinkedComputer.get(connection.computer2)).clearance + ") at Cost :"+ connection.cost +" Total Cost is:"+totalCost);

                hm.remove(connection.computer1);
                allLinked=linkSameClearance( hm);


                linkedComputerCount -= hm.size();
                if(hm.size()<=linkedComputerCount){
                    allLinked=true;
                    break;
                }
            } else if (hm.get(connection.computer2) != null && hmLinkedComputer.get(connection.computer1) != null && hmLinkedComputer.get(connection.computer2) == null ) {
                ((Computer) (hm.get(connection.computer2))).linked = true;
                hmLinkedComputer.put(connection.computer2, (Computer) hm.get(connection.computer2));
                totalCost += connection.cost;
                linkedComputerCount++;
                //print linked computers
                System.out.println("Computer " + connection.computer1 +
                        " (" + ((Computer) hmLinkedComputer.get(connection.computer1)).clearance + ") is connected to Computer "
                        + connection.computer2 + " (" + ((Computer) hm.get(connection.computer2)).clearance + ") at Cost :"
                        + connection.cost +" Total Cost is:"+totalCost);
                hm.remove(connection.computer2);

                allLinked=linkSameClearance( hm);

                linkedComputerCount -= hm.size();
                if(hm.size()<=linkedComputerCount){
                    allLinked=true;
                    break;
                }
            }

            if(hm.size()<=linkedComputerCount){
                allLinked=true;
                break;
            }

        }

        return allLinked;
    }


    public static void main(String[] args) {
        readFile();
        Collections.sort(connections, (c1, c2) -> c1.compaterTo(c2));

        for  (int i=1; i<4; i++){
            if(!linkSameClearance( (HashMap) HashHashComputer.get(i))){
                return;
            }
        }
        System.out.println(totalCost);
    }
}