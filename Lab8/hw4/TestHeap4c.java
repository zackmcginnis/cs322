class TestHeap4c {
  static final int S = 100;

  public static void main(String[] args) {
    Heap h = Heap.make(S);
    h.a = h.alloc(1);
    h.b = h.alloc(1);

    h.store(h.a, 1, h.b);
    h.store(h.b, 1, h.a);

    System.out.println("Before garbage collection;");
    h.dump();
    System.out.println("Free space remaining = " + h.freeSpace());

    h.garbageCollect();
    
    System.out.println("After garbage collection;");
    h.dump();
    System.out.println("Free space remaining = " + h.freeSpace());
  }
}