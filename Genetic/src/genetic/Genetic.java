/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

/**
 *
 * @author JUI
 */
import java.util.Scanner;
public class Genetic{
 public static void main(String[]args)
 {
   int [] random= new int[6];
   String [] chro= new String[6];
   int [] fit=new int[6];
   
   for(int i=0; i<6; i++)
   {
     random[i]=randInt(0, 15);
     String pp= Integer.toBinaryString(random[i]);
     chro[i]= String.format("%04d",Integer.valueOf(pp));
     fit[i]=fitness(random[i]);
   }
    int low1=fit[0];
    int noconsider1=0;
    for(int i=1; i<6; i++){
      if(low1>fit[i])
      {
        low1=fit[i];
        noconsider1=i;
      }
    }
    int low2;
    int noconsider2;
    
    if (noconsider1!=5){
     low2=fit[noconsider1+1];
     noconsider2= noconsider1+1;
    }
    else {
     low2=fit[0];
     noconsider2= 0;
    }
    for(int i=0; i<6; i++){
      if(low2>fit[i] && noconsider1!=i)
      {
        low2=fit[i];
        noconsider2=i;
      }
    }
    
    System.out.println("fitness1= "+low1+"index1=  "+noconsider1+" fitness2= "+low2+" index2=  "+noconsider2);
    
   
   
   for (int p=0; p<6; p++)
     System.out.println(random[p]+"   "+chro[p]+"  "+fit[p]);
   String []crossover= new String[6];
   int number= randInt(2, 6); //how many chromosome to mutate
   int c=0;
   int f=0;
   for(int i=0; i<6; i++)
   {
     for(int j=1; j<6; j++)
     {
     if(i!= noconsider1 && i!=noconsider2)
     {
       
       if(j!= noconsider1 && j!=noconsider2)
       {
         c++;
         if(c<4)
         {
         String cc=  crossOver(chro[i],chro[j]);
         //System.out.println(cc);
         crossover[f]=cc;
         f++;
         String dd=  crossOver(chro[j],chro[i]);
        // System.out.println(dd);
         crossover[f]=dd;
         f++;
         }
         else
         {
           break;
         }
       }
     }
   }
 }
   
   for(int i=0; i<f; i++){
     System.out.print(crossover[i]+"    ");
   }
   
   String []mutate= new String[number];
   int position= randInt(0,4);
    System.out.println("no of chro to mutate "+number+"  position: "+ position);
   for(int i=0; i<number; i++)
   {
     mutate[i]= mutation(crossover[i], position);
    
     System.out.println(mutate[i]);
   }
   
   int [] mutatefitness= new int [number];
   for( int i=0; i<number; i++)
   {
     //int k = Integer.parseInt(mutate[i]);
     int k = Integer.parseInt(mutate[i], 2);
     mutatefitness[i]=fitness( k);
   }
   
   
   int highfitness1=mutatefitness[0];
   int highindex1=0;
   int o=mutatefitness.length;
    for(int i=1; i<o; i++){
      if(highfitness1<mutatefitness[i])
      {
        highfitness1=mutatefitness[i];
        highindex1=i;
      }
    }
    int highindex2, highfitness2;
    if(highindex1==(o-1)){
       highindex2=0;
       highfitness2=mutatefitness[0];
    }
    else{
       highindex2=highindex1+1;
       highfitness2=mutatefitness[highindex2];
    }
    for(int i=0; i<o; i++)
    {
      if( mutatefitness[highindex2]<mutatefitness[i] && highindex1!=i )
      {
       highindex2=i;
      highfitness2=mutatefitness[highindex2];
      }
    }
    System.out.println(highfitness1+" position: "+highindex1+"  "+highfitness2+"  "+"position:"+highindex2);
    
    chro[noconsider1]=mutate[highindex1];
    chro[noconsider2]=mutate[highindex2];
    for(int i=0; i<chro.length; i++)
    {
      System.out.print(chro[i]+"  ");
    }
}   
 
 
 
 
 //random number generate...
 public static int randInt(int min, int max) {
   return min + (int)(Math.random() * max); 
}
 
 //Fitness test..
 public static int fitness(int x){
   return (15*x)-(x*x);
 }
 
 //Cross Over
 public static String crossOver(String p, String q)
 {
   // System.out.println(p +"   "+ q);
    char arr1[]=p.toCharArray();
    char arr2[]=q.toCharArray();
    int a=p.length();
    arr1[a-1]=arr2[a-1];
    arr1[a-2]=arr2[a-2];
    String b= String.valueOf(arr1);
   return b;
 }
 
 public static String mutation( String x, int y)
 {
   char aaa[]=x.toCharArray();
   if (aaa[y]=='1'){
     aaa[y]='0';
   }
   else {
     aaa[y]='1';
   }
   String aa=String.valueOf(aaa);
   return aa;
 }
   
}
