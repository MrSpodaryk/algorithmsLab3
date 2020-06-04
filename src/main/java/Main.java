import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    private static int vertexCount, ribsCount;
    private static int from;
    private static List<Integer> list = new ArrayList<>();
    private static int[][] graph;


    private static int minDistance(int[] distance, Boolean[] check) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertexCount; v++)
            if (!check[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }

        return minIndex;
    }

    private static double printPath(int[] distance) {
        double counter = 0;
        double suma = 0;
        for (int i = 0; i < vertexCount; i++) {
            if (distance[i] < 1000 && distance[i] > -1) {
                System.out.println(i + " --------- " + distance[i]);
                suma += distance[i];
                counter++;
            }
        }
        counter -= 1;
        System.out.println("suma = " + suma + " countere = " + counter + " avg = " + (suma / counter));
        return suma / counter;
    }

    static double dijkstra() {
        int[] distance;
        Boolean[] check;
        distance = new int[vertexCount];
        check = new Boolean[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            distance[i] = Integer.MAX_VALUE;
            check[i] = false;
        }

        distance[from] = 0;

        for (int count = 0; count < vertexCount; count++) {

            int u = minDistance (distance, check);
            check[u] = true;

            for (int v = 0; v < vertexCount; v++) {
                if (!check[v] && graph[u][v] != -11 &&
                        distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] <= distance[v])
                    distance[v] = distance[u] + graph[u][v];
            }
        }
        return printPath(distance);
    }

    static void init() {
        readFromFile();

        vertexCount = -1;
        for (int i = 0; i < list.size(); i += 3) {
            vertexCount = Math.max(vertexCount, Math.max(list.get(i), list.get(i + 1)));
        }
        vertexCount += 1;

        graph = new int[vertexCount][vertexCount];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = -11;
            }
        }

        for (int i = 0; i < list.size(); i += 3) {
            graph[list.get(i)][list.get(i + 1)] = list.get(i + 2);
        }
    }

    static void readFromFile() {
        try {
            FileReader reader = new FileReader("text.txt");
            BufferedReader br = new BufferedReader(reader);
            String st;
            int count = 0;
            while ((st = br.readLine()) != null) {
                for (String retval : st.split(" ")) {
                    if (count == 0) {
                        ribsCount = Integer.parseInt(retval);
                        count++;
                    } else if (count == 1) {
                        from = Integer.parseInt(retval);
                        count++;
                    } else {
                        list.add(Integer.parseInt(retval));
                    }
                }
            }
            System.out.println(" list =  " + list);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
        double dist = dijkstra();
        System.out.println(dist);
    }
}