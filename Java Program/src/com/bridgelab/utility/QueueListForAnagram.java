package com.bridgelab.utility;


public class QueueListForAnagram 
{
 private Node front,rear;
 
 public void insert(int val)
 {
  Node nd=new Node(val);
  
  if(front==null)
   front=nd;
  else
   rear.next=nd;
  
  rear=nd;
 }
 
 public  int delete()
 {
  if(front==null)return -1;
  int n=front.val;
  front=front.next;
  return n;
 }
	
 public void print() 
 {
  Node forword=front;
  int n=forword.val;
  
  while((n=delete())!=-1)
  {
   System.out.print(n+" ");	  
  }
 }	
	
 class Node
 {
  int val;
  Node next;
  
  Node(){}
  
  Node(int val)
  {
   this.val=val;	  
  }
 }
}
