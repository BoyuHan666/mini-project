import java.io.*;
import java.util.*;

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
            //TODO: implement this function and change the return statement.
        	// check if 0<=i<m
        	if(i>=this.m || i<0){
        		return -1;
        	}
        	// use the function provided to calculate the hash value
        	int g = ((((A*key)%(this.power2(w)))>>Math.abs(w-r))+i)%this.power2(r);
        return g;
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement.
        	for(int i=0;i<this.m;i++){
        		int bk = probe(key,i);
        		// check if the position is empty or the original element has been removed
        		if(this.Table[bk]==-1 || this.Table[bk]==-10){
        			this.Table[bk] = key;
        			return i;
        		}
        	}
        	return this.m;  
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            //TODO
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            //TODO: implement this and change the return statement
        	// num is the number of collision
        	int num = 0;
        	for(int i=0;i<this.m;i++){
        		int bk = probe(key,i);
        		// if find the key, then remove it and mark this position with -10
        		if(this.Table[bk]==key){
        			this.Table[bk] = -10;
        			break;
        		}
        		else{
        			// not find, then check next
        			num++;
        		}
            }   
            return num;
        }
}