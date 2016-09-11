import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTests = sc.nextInt();
        for(int j = 0; j < numTests; j++) {
            runTest(sc);
        }
    }
        
    public static void runTest(Scanner sc) {
        int numVertices = sc.nextInt(), numEdges = sc.nextInt();
        long[][] adjMat = new long[numVertices + 1][numVertices + 1];
        initializeMatrix(adjMat, numVertices, numEdges, sc);
        Map<Integer, Long> distances = getDistancesFromSource(adjMat, sc);
        for(int i = 1; i < numVertices + 1; i++) {
            if(distances.get(i) == null) System.out.print("-1 ");
            else {
                Long dist = distances.get(i);
                if(dist != 0) System.out.print(Long.toString(dist) + " ");
            }
        }
        System.out.println();
    }
        
    public static Map<Integer, Long> getDistancesFromSource(long[][] adjMat, Scanner sc) {
        int source = sc.nextInt();            
        Set<Integer> unvisited = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        unvisited.add(source);
        Map<Integer, Long> distances = new HashMap<>();
        distances.put(source, 0L);
        while(!unvisited.isEmpty()) {
            int curr = findMinimumVertex(unvisited, distances);
            if(curr == -1) continue;
            long[] edgeWeights = adjMat[curr];
            unvisited.remove(curr);
            visited.add(curr);
            findNeighborDistances(distances, edgeWeights, unvisited, visited, curr);
        }
        return distances;
    }
        
    public static void findNeighborDistances(Map<Integer, Long> distances, long[] edgeWeights,
                                             Set<Integer> unvisited, Set<Integer> visited, int curr) {
        for(int i = 1; i < edgeWeights.length; i++) {
            if(!visited.contains(i)) {
                long newDist = (distances.get(curr) != null) ? distances.get(curr) + edgeWeights[i] : 45000000000L;
                long currDist = (distances.get(i) != null) ? distances.get(i) : 45000000000L;                        
                if(currDist > newDist) {
                    distances.put(i, newDist);
                    unvisited.add(i);
                }
            }
        }
    }    
        
    public static int findMinimumVertex(Set<Integer> unvisitedVertices, Map<Integer, Long> distances) {
        long minDistance = 45000000000L;
        int curr = -1;
        for(int vertex : unvisitedVertices) {
            if(distances.get(vertex) != null) {
                long currDistance = distances.get(vertex);
                if(currDistance < minDistance) {
                    minDistance = currDistance;
                    curr = vertex;
                }
            }
        }
        return curr;
    }
        
    public static void initializeMatrix(long[][] adjMat, int numVertices, int numEdges, Scanner sc) {
        //initialize all values in the array to 10001 so that they will be larger than the largest weight
        for(int i = 1; i < numVertices + 1; i++) Arrays.fill(adjMat[i], 45000000000L);
        for(int i = 0; i < numEdges; i++) {
            int vertex1 = sc.nextInt(), vertex2 = sc.nextInt(), weight = sc.nextInt();
            if(adjMat[vertex1][vertex2] > weight)  {
                adjMat[vertex1][vertex2] = weight;
                adjMat[vertex2][vertex1] = weight;
            }
        }
    }
}