public class RandomIndex{
    //DATA:
	public int[] index;
	public int count = 0;

    //ACTIONS:
	public void setSize(int N){
		if (index == null || N != index.length){
			index = new int[N];
			initializeIndex();
			permuteIndex();
		}
	}
	
	public void initializeIndex(){
		for(int i=0;i<index.length;i++)
			index[i] = i;
	}

	public void permuteIndex(){
		java.util.Random rnd = new java.util.Random();
		for(int i=index.length-1;i>=0;i--){
			int j = rnd.nextInt(i+1);
			int tmp = index[j];
			index[j] = index[i];
			index[i] = tmp;
		}
	}

	public int getNext(){
		int res = index[count++];
		if (count == index.length){
			permuteIndex();
			count = 0;
		}
		return res;
	}
}
