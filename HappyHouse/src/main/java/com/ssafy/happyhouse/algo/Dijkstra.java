package com.ssafy.happyhouse.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

import com.ssafy.happyhouse.model.ShopDto;

public class Dijkstra {
	
	
	
	public static List<ShopDto> dijkstra(List<ShopDto> beforeList) throws Exception {
		

		int V = beforeList.size();
		double[][] adjMatrix = new double[V][V];
		int start = 0;
		
		StringTokenizer st;
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = getDistance(beforeList.get(i), beforeList.get(j));
			}
		}

		double[] distance = new double[V]; // 출발지에서 자신으로 오는 최소 비용
		boolean[] isVisited = new boolean[V]; // 최소 비용 확정 여부
		int[] parent = new int[V];
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0; // 시작점 0으로
		pq.offer(new Vertex(start, distance[start]));
		
		
		
		while(!pq.isEmpty()) {
			// 단계1 최소 비용이 확정되지 않은 정점 중 최소 비용 정점 선택
			Vertex current = pq.poll();
			if(isVisited[current.no]) continue;
			isVisited[current.no] = true;
			// 단계2: 선택된 정점을 경유지로 하여 아직 최소 비용이 확정되지 않은 다른 정점의 최소비용을 고려

			for (int j = 0; j < V; j++) {
				if (!isVisited[j] && adjMatrix[current.no][j] != 0
						&& distance[j] > distance[current.no] + adjMatrix[current.no][j]) {
					distance[j] = distance[current.no] + adjMatrix[current.no][j];
					pq.offer(new Vertex(j, distance[j]));
					parent[j] = current.no;
				}
			}

		}
		
		Stack<Integer> stack = searchPath(parent, 0, V-1);
		StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            int city = stack.pop();
            sb.append(city + " ");
        }
		
		System.out.println(Arrays.toString(distance));
		System.out.println(sb.toString());
		
		return null;
	}
	

	
	static double getDistance(ShopDto i, ShopDto you) {
		return (i.getLat() - you.getLat()) * (i.getLat() - you.getLat()) + (i.getLng() - you.getLng()) * (i.getLng() - you.getLng());
	}
	
	static class Vertex implements Comparable<Vertex>{
		int no;
		double minDistance;

		public Vertex(int no, double minDistance) {
			super();
			this.no = no;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return Double.compare(minDistance, o.minDistance);
		}
		
	}
	public static Stack<Integer> searchPath(int[] parent, int start, int end){
        Stack<Integer> stack = new Stack<>();
        int cur = end;

        while(cur != start) {
            stack.push(cur);

            cur = parent[cur];
        }
        stack.push(cur);

        return stack;
    }
	
}
