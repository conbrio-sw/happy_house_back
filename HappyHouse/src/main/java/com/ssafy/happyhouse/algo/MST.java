package com.ssafy.happyhouse.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

import com.ssafy.happyhouse.model.ShopDto;

public class MST {
	
	
	
	public static List<Integer> mst(List<ShopDto> beforeList) throws Exception {
		

		int V = beforeList.size();
		
		ArrayList<ArrayList<Vertex>> vertex = new ArrayList<ArrayList<Vertex>>();
		for (int i = 0; i < V; i++) {
			vertex.add(new ArrayList<>());
		}

		
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				double w = getDistance(beforeList.get(i), beforeList.get(j));
				vertex.get(i).add(new Vertex(j, w));
				vertex.get(j).add(new Vertex(i, w));
			}
		}
		
		
		
		
		double[] distance = new double[V]; // 출발지에서 자신으로 오는 최소 비용
		boolean[] isVisited = new boolean[V]; // 최소 비용 확정 여부
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		
		int cnt = 0; // 시작 정점 선택
		isVisited[cnt] = true; // 선택한 시작 정점 방문
		pq.addAll(vertex.get(cnt)); // 시작 정점에서 갈 수 없는 모든 간선 큐에 넣어주기
		
		List<Integer> rst = new ArrayList<Integer>();
		rst.add(cnt);
		
		while (!pq.isEmpty()) {
			Vertex edge = pq.poll();
			if (isVisited[edge.no])
				continue;
			isVisited[edge.no] = true;
			rst.add(edge.no);
			pq.addAll(vertex.get(edge.no));
			cnt++;
			if(cnt == V) break; 
		}
		System.out.println("MST : " + rst.toString());
		return rst;
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
