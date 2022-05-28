import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class US_elections {
	public static int solution(int num_states, int[] delegates, int[] votes_Biden, int[] votes_Trump, int[] votes_Undecided){
		//vote pre-treatment
		int state_votes[] = new int [num_states];
		int remain = 0;
		int max_vote = 0;
		for(int i = 0; i < num_states; i++){
			int min = 0;
			int total_votes = votes_Biden[i]+votes_Trump[i]+votes_Undecided[i];
			//check if Biden has already get the delegates of the i-th state
			if(votes_Biden[i]*2>total_votes){
				min = 0;
			}
			//check if Trump has already get the delegates of the i-th state
			else if(votes_Trump[i]*2>=total_votes){
				min = -1;
			}else{
				//if it is not clear that who will win this state
				//calculate the minimal votes Biden needed to win
				min = (total_votes)/2-votes_Biden[i]+1;
				remain++;
			}
			max_vote += votes_Undecided[i];
			state_votes[i] = min;
		}
		
		int n1=0;//Biden
		int n2=0;//Trump
		int total=0;//sum of delegates
		int tmp=0;
		int [] value = new int[remain];//the votes of remaining states which we do not know who will win
		int [] weight = new int[remain];//the delegates of remaining states which we do not know who will win
		for(int i = 0; i < num_states; i++){
			total += delegates[i];
			if(state_votes[i]==0){
				n1 += delegates[i];
			}
			else if(state_votes[i]==-1){
				n2 += delegates[i];
			}
			else{
				value[tmp] = state_votes[i];
				weight[tmp] = delegates[i];
				tmp++;
			}
		}
		//check if Trump has already won
		if(n2 >= total/2.0){
			return -1;
		}
		//check if Trump has already won
		if(n1 >= total/2.0 + 1.0){
			return 0;
		}
		
		int size = total/2+1-n1+1;
		int array[][] = new int [remain][size];
		//dynamic programming to find minimal votes Biden needed to win
		for(int i=0;i<remain;i++){
			for(int j=0;j<size;j++){
				//initialization
				if(i==0){
					if(j==0){
						array[i][j]=0;
					}else{
						if(j>weight[0]) {
							array[0][j] = max_vote; 
						}else{
							array[0][j] = value[0];
						}
					}
				}
				else{
					if(j > weight[i]) {
						int limit = j-weight[i];
						if(array[i-1][j]<array[i-1][limit]+value[i]){
							array[i][j] = array[i-1][j];
						}else{
							array[i][j] = array[i-1][limit]+value[i];
						}
					}else{
						if(array[i-1][j]<value[i]){
							array[i][j] = array[i-1][j];
						}else{
							array[i][j] = value[i];
						}
					}
				}
			}
		}
		
		return array[remain-1][size-1];
	}

	public static void main(String[] args) {
		 try {
				String path = args[0];
	      File myFile = new File(path);
	      Scanner sc = new Scanner(myFile);
	      int num_states = sc.nextInt();
	      int[] delegates = new int[num_states];
	      int[] votes_Biden = new int[num_states];
	      int[] votes_Trump = new int[num_states];
	 			int[] votes_Undecided = new int[num_states];	
	      for (int state = 0; state<num_states; state++){
				  delegates[state] =sc.nextInt();
					votes_Biden[state] = sc.nextInt();
					votes_Trump[state] = sc.nextInt();
					votes_Undecided[state] = sc.nextInt();
	      }
	      sc.close();
	      int answer = solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
	      	System.out.println(answer);
	    	} catch (FileNotFoundException e) {
	      	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    	}
	  	}

	}