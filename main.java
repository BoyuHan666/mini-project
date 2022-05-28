import java.io.*;
import java.util.*;


public class main {     

     
    public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.
    	
    	// build Chaining and open_Addressing
    	Chaining ch = new Chaining(10, 0, -1);
    	int[] a = {1,2,2,3,4,5,6,7,8};
    	ch.insertKeyArray(a);
    	Open_Addressing op = new Open_Addressing(10, 0, -1);
    	op.insertKeyArray(a);
    	
    	// test chain in chaining
    	if(ch.chain(1)==30){
    		System.out.println("chain_pass1");
    	}
    	if(ch.chain(4)==25){
    		System.out.println("chain_pass2");
    	}
    	if(ch.chain(8)==19){
    		System.out.println("chain_pass3");
    	}
    	
    	// test insert key in Chaining 
    	if(ch.insertKey(9)==0){
    		System.out.println("CH_Insert_pass1");
    	}
    	if(ch.insertKey(9)==1){
    		System.out.println("CH_Insert_pass2");
    	}
    	if(ch.insertKey(2)==2){
    		System.out.println("CH_Insert_pass3");
    	}
    	
    	// test probe in open_Addressing
    	if(op.probe(1, 0)==30){
    		System.out.println("probe_pass1");
    	}
    	if(op.probe(1, 1)==31){
    		System.out.println("probe_pass2");
    	}
    	if(op.probe(1, 3)==1){
    		System.out.println("probe_pass3");
    	}
    	
    	// test insert key in open_Addressing
    	if(op.insertKey(1)==1){
    		System.out.println("OP_Insert_pass1");
    	}
    	if(op.insertKey(1)==2){
    		System.out.println("OP_Insert_pass2");
    	}
    	if(op.insertKey(2)==5){
    		System.out.println("OP_Insert_pass3");
    	}
    	
    	// test remove key in open_Addressing
    	if(op.removeKey(1)==0){
    		System.out.println("OP_Remove_pass1");
    	}
    	if(op.removeKey(1)==1){
    		System.out.println("OP_Remove_pass2");
    	}
    	if(op.removeKey(100)>=op.m){
    		System.out.println("OP_Remove_pass3");
    	}
    	
    }
}