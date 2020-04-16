package cse311programming2;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class Player {

	private int id, score, points;
	private String ptype, lastmove;

	public Player (int id, int score, String ptype, String lastmove, int points) {
		this.id=id;
		this.score=score;
		this.ptype=ptype;
		this.lastmove=lastmove;	
		this.points=points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPtype() {
		return ptype;
	}

	public String getLastmove() {
		return lastmove;
	}

	public void setLastmove(String lastmove) {
		this.lastmove = lastmove;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}


	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public static void calcScore(Player a, Player b) {
		if (a.lastmove=="cooperate"&&b.lastmove=="cooperate") {
			a.setScore(3);
			b.setScore(3);
		}
		if (a.lastmove=="cooperate"&&b.lastmove=="defect") {
			a.setScore(0);
			b.setScore(5);
		}
		if (a.lastmove=="defect"&&b.lastmove=="cooperate") {
			a.setScore(5);
			b.setScore(0);
		}
		if (a.lastmove=="defect"&&b.lastmove=="defect") {
			a.setScore(1);
			b.setScore(1);
		}
	}

	public void  setmove(Player a){
		if (a.ptype=="t4t"&&this.ptype=="t4t") {
			//			String alast=a.lastmove;
			//			a.setLastmove(this.lastmove);
			//			this.setLastmove(alast);
			a.setLastmove("cooperate");
			this.setLastmove("cooperate");
		}
		if (a.ptype=="g"&&this.ptype=="t4t") {
			this.setLastmove(a.lastmove);
			if (this.lastmove=="defect") {
				a.setLastmove("defect");
			} else {
				a.setLastmove("cooperate");
			}
		}
		if (a.ptype=="t4t"&&this.ptype=="g") {
			a.lastmove=this.lastmove;
			if (a.lastmove=="defect") {
				this.setLastmove("defect");
			} else {
				this.setLastmove("cooperate");
			}
		}
		if(a.ptype=="g"&&this.ptype=="g") {
			if (a.lastmove=="defect") {
				this.setLastmove("defect");
			} else {
				this.setLastmove("cooperate");
			}
			if (this.lastmove=="defect") {
				a.setLastmove("defect");
			} else {
				a.setLastmove("cooperate");
			}
		}
		if (a.ptype=="g"&&this.ptype=="ad") {
			a.setLastmove("defect");
		}
		if (a.ptype=="ad"&&this.ptype=="g") {
			this.setLastmove("defect");
		}
		if(a.ptype=="t4t"&&this.ptype=="ad") {
			a.setLastmove("defect");
		}
		if(a.ptype=="ad"&&this.ptype=="t4t") {
			this.setLastmove("defect");
		}
		if((a.ptype=="ac"&&this.ptype=="ac")||(a.ptype=="t4t"&&this.ptype=="ac")||(a.ptype=="ac"&&this.ptype=="t4t")||(a.ptype=="g"&&this.ptype=="ac")||(a.ptype=="ac"&&this.ptype=="g")) {
			a.setLastmove("cooperate");
			this.setLastmove("cooperate");
		}
		if(a.ptype=="ac"&&this.ptype=="ad") {
			a.setLastmove("cooperate");
			this.setLastmove("defect");
		}
		if(a.ptype=="ad"&&this.ptype=="ac") {
			a.setLastmove("defect");
			this.setLastmove("cooperate");
		}
		calcScore(a,this);
		//		if (a.ptype=="g"||this.ptype=="g") {
		//			a.setLastmove("cooperate");
		//			this.setLastmove("cooperate");
		//		}
		//		if (a.ptype=="t4t"||this.ptype=="t4t") {
		//			a.setLastmove("cooperate");
		//			this.setLastmove("cooperate");
		//		}
		//		if (a.ptype=="ac"||this.ptype=="ac") {
		//			a.setLastmove("cooperate");
		//			this.setLastmove("cooperate");
		//		}
		//		if (a.ptype=="ad"||this.ptype=="ad") {
		//			a.setLastmove("defect");
		//			this.setLastmove("defect");
		//		}
	}
	public static void reset(Player [] array) {
		for (int i=0;i<array.length;i++) {
			if(array[i].getPtype()=="g"||array[i].getPtype()=="ac"&&array[i].getPtype()=="t4t") {
				array[i].setLastmove("cooperate");
			}
			if(array[i].getPtype()=="ad") {
				array[i].setLastmove("defect");
			}
		}
	}

	public static void points(int[] array, Player[]rank) {
		for (int i=0;i<array.length;i++) {
			rank[i].setPoints(array[i]);
		}
	}

	public static Player[] sortArray(Player[]array) {
		Player temp;
		for (int i=0;i<array.length;i++) {
			for (int j=i;j<array.length-1;j++) {
				if (array[i].getPoints()<array[j+1].getPoints()) {
					temp=array[j+1];
					array[j+1]=array[i];
					array[i]=temp;
				}
			}
		}
		return array;
	}
	public static int getMin(Player []array) {
		int min=array[0].getPoints();
		for (int i=1;i<array.length;i++) {
			if(!array[i].getPtype().equals("fix") ) {
				if(array[i].getPoints()<min&&array[i].getPoints()!=0) {
					min=array[i].getPoints();
				}
			}
		}
		return min;
	}
	public static int getMax(Player []array) {
		int max=array[0].getPoints();
		for (int i=1;i<array.length;i++) {
			if(array[i].getPoints()>max) {
				max=array[i].getPoints();
			}
		}
		return max;
	}

	public static int chooseIndex(ArrayList<Integer>list, int pindex) {
		int x=(int)(Math.random()*pindex);
		int help=0;
		if (list.isEmpty()==false&&x<list.size()) {
			help=list.get(x);
			list.remove(x);
		}
		return help;
	}
	public List<Integer> findIndices(Player[]array) {
		List<Integer> x = new ArrayList<Integer>();
		for (int i=0;i<array.length;i++) {
			if (array[i].getPtype().equals("fix")) {
				x.add(i);
			}
		}
		return x;
	}

	public static void removeBot(Player[]array, int p) {
		Player delete=new Player(0,0,"fix","",0);
		int pindex=(int) (array.length*((double)p/100));
		ArrayList<Integer>indices=new ArrayList<Integer>();
		for (int i=0;i<pindex;i++) {
			indices.add(i);
		}
		int counter=0;
		int min=getMin(array);

		for (int i=array.length-1;i>=0;i--) {
			if(pindex!=0) {
//				System.out.println("im at index "+i);
				if (array[i].getPoints()==min) {
					counter++;
//					System.out.println("i'm counting!"+counter);

				}
				if (pindex>counter&&array[i].getPoints()==min) {
					//if # need to replace is more than the min, replace w holder
					array[i]=delete;
//					System.out.println("hello"+pindex);
					//					min=getMin(array);
					pindex--;
//					System.out.println("here#"+pindex);
				}
				if (pindex==counter) {
					//if # need to replace is more than the min, replace w holder
					if(array[i].getPoints()==min) {
						array[i]=delete;
//						System.out.println("here"+pindex);	
						pindex--;
//						System.out.println("here~"+pindex);
					}


				}
				if(min!=getMin(array)) {
					counter=0;
//					System.out.println("hi");
					min=getMin(array);
				}

				if(pindex<counter&&array[i].getPoints()==getMin(array)) {
					array[i]=delete;
					pindex--;
				}
				if(pindex<counter) {
//					System.out.println("am i here?");
					int inds=chooseIndex(indices,counter);
					if(array[inds].getPoints()==min) {
						array[inds]=delete;
						pindex--;
//						System.out.println("oh");
					}
				}
			}
		}
	}

	public static Player[] replaceTop(Player[] array,int p) {
		int pindex=(int) (array.length*((double)p/100));
		//		int pindex=2;
		ArrayList<Player> top = new ArrayList<Player>();
		ArrayList<Integer>indices=new ArrayList<Integer>();
		for (int i=0;i<pindex;i++) {
			indices.add(i);
		}
		int inds=0;
		int counter=0;
		for (int i=0;i<array.length;i++) {
			if (pindex!=0) {
				int max=getMax(array);
				if (array[i].getPoints()==max) {
					counter++;
					//										System.out.println("I am counter: "+counter);
				}
				if(pindex>=counter&&array[i].getPoints()==max) {
					top.add(array[i]);
					array[i].setPoints(0);
					//					top.add(array[i]);
					pindex--;
					//					System.out.println("points:"+array[inds].getPoints());
					//					System.out.println("yay! here!");

				}
				if(max!=getMax(array)) {
					counter=0;
					max=getMax(array);
					//					System.out.println("I am counter: "+counter);
					//					System.out.println("look here!");
				}
				if(pindex<counter) {
					inds=chooseIndex(indices,counter);
					if (!top.contains(array[inds])&&array[inds].getPoints()==max) {
						top.add(array[inds]);
						array[inds].setPoints(0);
						pindex--;
						//					System.out.println("i'm here!");
					}
				}
				//				System.out.println("I am pindex: "+pindex);
			}
		}
//		System.out.println("top");
		for(int i=0;i<top.size();i++) {
//			System.out.println(top.get(i).getId()+": "+top.get(i).getPtype()+"  ");
		}
		//		System.out.println("hi");
		pindex=(int) (array.length*((double)p/100));
		for (int i=0;i<array.length;i++) {
			if (array[i].getPtype()=="fix"&&top.isEmpty()==false) {
				array[i].setPtype(top.get(0).getPtype());
				//				System.out.println("before! "+ top.get(0).getId());
				top.remove(0);
				//				System.out.println("after! "+ top.get(0).getId());
				//				System.out.println("hi:"+array[i].getId());
			}
		}
		//	for (int i=0;i<pindex;i++) {
		//		indices.add(i);
		//	}
		return array;
	}

	public static void main(String[] args) {
		int n=4;
		int p=25;
		int k=3; 
		int m=5;
		Player [] list = new Player[n];
		String[] moves= {"cooperate","defect"};
		//populate list of players array 
		for (int i=0;i<n;i++) {
			String ptype="";
			String lastmove="";
			if (i%4==0) {
				ptype="t4t";
			}
			if (i%4==1) {
				ptype="g";
			}
			if (i%4==2) {
				ptype="ac";
			}
			if (i%4==3) {
				ptype="ad";
			}
			if (ptype=="ad") {
				lastmove=moves[1];
			}else {
				lastmove=moves[0];
			}
			list[i]=new Player(i, 0,ptype,lastmove,0);
		}

		//run the game k*nC2 times
		int []scores=new int[n];
		int count=0;
		int [] tracker1 = new int[k];
		int [] tracker2 = new int[k];
		int [] tracker3 = new int[k];
		int [] tracker4 = new int[k];

		for (int x=0;x<k;x++) {
			reset(list);
			System.out.println("Gen "+(x+1)+":");
			int genscore=0;
			for (int i=0;i<n;i++) {
				int p1genscore=0;
				for (int j=i+1;j<n;j++) {
					//					reset(list);
										System.out.println("game #"+(count+1)+"\t  ");

					for (int y=0;y<m;y++) {

						list[i].setmove(list[j]);
						p1genscore=p1genscore+list[i].getScore();
												System.out.print(list[i].getId()+" v "+list[j].getId()+"  ("+list[i].getScore()+","+list[j].getScore()+") \t");
												System.out.println(list[i].getPtype()+" v "+list[j].getPtype());
						if(j==list[i].getId()) {
							scores[i]+=list[j].getScore();
						}
						if(i==list[i].getId()) {
							scores[i]+=list[i].getScore();
						}
						if(j==list[j].getId()) {
							scores[j]+=list[j].getScore();
						}
						if(i==list[j].getId()) {
							scores[j]+=list[i].getScore();
						}
						//					System.out.println("\ti:"+i+" j:"+j);
						count++;
					}
					reset(list);

				}
			}
			//			System.out.println("games played:"+count);

			//giving each person points for that round
			points(scores,list);
			for (int i=0;i<n;i++) {
				//				System.out.println(list[i].getPoints());
				genscore+=scores[i];
			}
			//						System.out.println("Gen "+k+ " total score: "+genscore);
			//count each type of player: 
			int t4t=0;
			int t4tscore=0;
			int g=0;
			int gscore=0;
			int ac=0;
			int acscore=0;
			int ad=0;
			int adscore=0;
			for (int i=0;i<n;i++) {
				if (list[i].getPtype()=="t4t") {
					t4t++;
					t4tscore+=list[i].getPoints();
				}
				if (list[i].getPtype()=="g") {
					g++;
					gscore+=list[i].getPoints();
				}
				if (list[i].getPtype()=="ac") {
					ac++;
					acscore+=list[i].getPoints();
				}
				if (list[i].getPtype()=="ad") {
					ad++;
					adscore+=list[i].getPoints();
				}
			}
			tracker1[x]=t4t;
			tracker2[x]=g;
			tracker3[x]=ac;
			tracker4[x]=ad;

			//			System.out.println("T4t: "+Math.round((double)t4t/n*100)+"%   G: "+Math.round((double)g/n*100)+"%   AC: "
			//					+Math.round((double)ac/n*100)+"%   AD: "+Math.round((double)ad/n*100)+"%  "
			//					+ "total: "+genscore);
			//			System.out.println("\t   "+t4tscore+"     "+gscore+"      "+acscore+"       "+adscore);
			//			System.out.println("\t   "+Math.round((double)t4tscore/t4t)+"     "+Math.round((double)gscore/g)+"     "
			//					+Math.round((double)acscore/ac)+"     "+Math.round((double)adscore/ad));

			//rank players based on score 
			sortArray(list);
			System.out.println("before deleting");
			for (int i=0;i<n;i++) {
				//				list[i].setId(i);
				System.out.println(list[i].getPtype()+": "+list[i].getPoints());
			}
			removeBot(list,p);
//			System.out.println("after deleting");
			for (int i=0;i<n;i++) {
//				System.out.println(list[i].getPtype()+": "+list[i].getPoints());
			}
			//			sortArray(list);
			list=replaceTop(list,p);
			sortArray(list);

//			System.out.println("after replacing");
			for (int i=0;i<n;i++) {
//				System.out.println(list[i].getPtype()+": "+list[i].getPoints());
				list[i].setId(i);
			}
		}

//				System.out.println("1: ");
//				for (int i=0;i<k;i++) {
//					System.out.print(tracker1[i]+", ");
//				}
//				System.out.println("\n2: ");
//		
//				for (int i=0;i<k;i++) {
//					System.out.print(tracker2[i]+", ");
//				}
//				System.out.println("\n3: ");
//		
//				for (int i=0;i<k;i++) {
//					System.out.print(tracker3[i]+", ");
//				}
//				System.out.println("\n4: ");
//		
//				for (int i=0;i<k;i++) {
//					System.out.print(tracker4[i]+", ");
//				}
	}
}




