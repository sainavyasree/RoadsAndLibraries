 import java.util.*;
 
public class RAndL {
        public static void main(String[] args) {
            Scanner ob = new Scanner(System.in);
            int q = ob.nextInt();
            for(int a0 = 0; a0 < q; a0++){
                int n = ob.nextInt();
                int m = ob.nextInt();
                long cl = ob.nextInt();
                long cr = ob.nextInt();
                Node[] cities = new Node[n];
                for (int i = 0 ; i < n ; i++) {
                    cities[i] = new Node();
                    cities[i].id = i + 1;
                    cities[i].groupId = -1;
                }
                for(int a1 = 0; a1 < m; a1++){
                    int city_1 = ob.nextInt();
                    int city_2 = ob.nextInt();
                    cities[city_1 - 1].connected.add(cities[city_2-1]);
                    cities[city_2 - 1].connected.add(cities[city_1-1]);
                }
                if (cr > cl) {
                    System.out.println(cl * n);
                } else {
                    int groupCounter = 0;
                    boolean allGrouped = false;
                    while (!allGrouped) {
                        allGrouped = true;
                        for (int i = 0 ; i < n ; i++) {
                            Node curCity = cities[i];
                            if (curCity.groupId == -1) {
                                allGrouped = false;
                                boolean findInside = false;
                                for (int j = 0 ; j < curCity.connected.size() ; j++) {
                                    if (curCity.connected.get(j).groupId != -1) {
                                        curCity.groupId = curCity.connected.get(j).groupId;
                                        findInside = true;
                                    }
                                }
                                if (!findInside) {
                                    curCity.groupId = groupCounter++;
                                }
                                for (int j = 0 ; j < curCity.connected.size() ; j++) {
                                    if (curCity.connected.get(j).groupId == -1) {
                                        curCity.connected.get(j).groupId = curCity.groupId;
                                        updateGroupId(curCity.connected.get(j));
                                    }

                                }
                            }
                        }
                    }
                    System.out.println((n-groupCounter)*cr + groupCounter*cl);
                }
            }
            ob.close();
        }
        
        static void updateGroupId(Node curCity) {
            for (int j = 0 ; j < curCity.connected.size() ; j++) {
                if (curCity.connected.get(j).groupId == -1) {
                    curCity.connected.get(j).groupId = curCity.groupId;
                    updateGroupId(curCity.connected.get(j));
                }
               
            }
        }
    }

    class Node {
        int id;
        int groupId;
        List<Node> connected = new ArrayList<Node>();
    }
