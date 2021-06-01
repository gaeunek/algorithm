package samsung;

import java.io.*;
import java.util.*;

// 이쪽이 더 시간도 빠르고 메모리도 적다
public class Main16235 {
	static int N, M, K;
	static int[][] A, map;
	static int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static ArrayList[][] tree;

	static boolean check(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		map = new int[N][N];
		tree = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; // 초기 양분은 5이다.
				tree[i][j] = new ArrayList<Integer>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			tree[x][y].add(age);
		}

		for (int i = 0; i < K; i++) {
			int[][] deadTree = spring();
			summer(deadTree);
			fall();
			winter();
		}
		
		System.out.println(checkLivingTree());
	}

	private static int checkLivingTree() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(tree[i][j].size() > 0) result += tree[i][j].size();
			}
		}
		
		return result;
	}

	public static int[][] spring() {
		int[][] deadTree = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tree[i][j].size() > 0) {

					Collections.sort(tree[i][j]);
					int size = tree[i][j].size();
					while(size-- > 0) {
						int age = (int) tree[i][j].remove(0);
						int nutrient = map[i][j] - age;
						if (nutrient >= 0) {
							map[i][j] = nutrient;
							tree[i][j].add(age + 1);
						} else {
							deadTree[i][j] += age / 2;
						}
					}
				}
			}
		}

		return deadTree;
	}

	public static void summer(int[][] deadTree) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (deadTree[i][j] > 0)
					map[i][j] += deadTree[i][j];
			}
		}
	}

	public static void fall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tree[i][j].size() > 0) {
					for (int k = 0; k < tree[i][j].size(); k++) {
						int age = (int) tree[i][j].get(k);
						if(age % 5 == 0) { // 나이가 5의 배수인 경우에만 번식
							
							for (int d = 0; d < 8; d++) {
								int ni = i + dir[d][0];
								int nj = j + dir[d][1];
								
								if(check(ni, nj)) {
									tree[ni][nj].add(1);
								}
							}
							
						}
					}
				}
			}
		}
	}
	
	public static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
	
	public static void print() {
		System.out.println("남아 있는 양분(map)");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("심어져 있는 나무");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(tree[i][j].size() > 0) {
					System.out.println("["+i+", "+j+"]에 : "+tree[i][j]+", 총"+tree[i][j].size()+"개");
				}
			}
		}
		System.out.println();
	}
	
	public static void print2(int[][] arr) {
		System.out.println("죽은 나무 양분 저장 배열");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
