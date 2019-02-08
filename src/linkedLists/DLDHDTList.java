package linkedLists;

public class DLDHDTList<E> implements LinkedList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		// ADD CODE HERE to generate empty linked list of this type 
		header= new DNode();
		trailer = new DNode();
		header.setNext(trailer);
		trailer.setPrev(header);
		length=0;
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// ADD CODE HERE
		DNode<E> dnuevo = (DNode<E>) nuevo;
		DNode<E> nAfter = (DNode<E>) target;
		DNode<E> nBefore = (DNode<E>) nAfter.getPrev();
		
		if(nAfter.equals(header)) {
			header=dnuevo;
		}
		else {
			nBefore.setNext(dnuevo);
			dnuevo.setNext(nAfter);
		}

	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NodeOutOfBoundsException {
		if (length == 0) 
			throw new NodeOutOfBoundsException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NodeOutOfBoundsException {
		if (length == 0) 
			throw new NodeOutOfBoundsException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NodeOutOfBoundsException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		DNode<E> aNode = ((DNode<E>) target).getNext(); 
		if (aNode == null)  {
			throw new NodeOutOfBoundsException("getNextNode(...) : target is the last node."); }
		else {
			return aNode;
			}
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NodeOutOfBoundsException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if (target == header) {
			throw new NodeOutOfBoundsException("getNextNode(...) : target is the first node.");   
		}
		else {
		
			DNode<E> prev = header; 
			while (prev != null && prev.getNext() != target) {
				prev = prev.getNext();  }
			return prev; 
		}
 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		// ADD CODE HERE 
		DNode<E>dtarget = (DNode<E>) target;
		DNode<E> prevn = dtarget.getPrev();
		DNode<E> nextn = dtarget.getNext();
		prevn.setNext(dtarget.getNext());
		nextn.setPrev(dtarget.getPrev());
		dtarget.cleanLinks();
		length--;
		

	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.setElement(null); 
			header.cleanLinks(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
		this.destroy();
		header=null;
		trailer=null;
		length=0;

	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}

	/**
	 * Class to represent a node of the type used in doubly linked lists. 
	 * @author pedroirivera-vega
	 *
	 * @param <E>
	 */
	private static class DNode<E> implements Node<E> {
		private E element; 
		private DNode<E> prev, next; 

		// Constructors
		public DNode() {}
		
		public DNode(E e) { 
			element = e; 
		}
		
		public DNode(E e, DNode<E> p, DNode<E> n) { 
			prev = p; 
			next = n; 
		}
		
		// Methods
		public DNode<E> getPrev() {
			return prev;
		}
		public void setPrev(DNode<E> prev) {
			this.prev = prev;
		}
		public DNode<E> getNext() {
			return next;
		}
		public void setNext(DNode<E> next) {
			this.next = next;
		}
		public E getElement() {
			return element; 
		}

		public void setElement(E data) {
			element = data; 
		} 
		
		/**
		 * Just set references prev and next to null. Disconnect the node
		 * from the linked list.... 
		 */
		public void cleanLinks() { 
			prev = next = null; 
		}
		
	}

}
