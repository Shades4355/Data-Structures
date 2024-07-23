/** Program: DLL project
Written by: Julia Kelly
Program Description: interface for DLL 
Challenges: N/A
Time Spent: 1 min

Revision Log
Date:                   By:                  Action:
---------------------------------------------------
7/25/2020      			jk                   initial creation of file
9/9/2021				jk					 edited description

**/

public interface DLLADT<E> {

	abstract void addFirst(E data);	
	abstract void addLast(E data);
	abstract E first() ;
	abstract E last();
	abstract int size() ;
	abstract E removeFirst() ;	
	abstract E removeLast() ;
	abstract boolean isEmpty() ;
}
