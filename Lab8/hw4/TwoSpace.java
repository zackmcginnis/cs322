class TwoSpace extends Heap {
  protected int[] toSpace;        // Pointer to toSpace

  /** Construct a new TwoSpace collected heap of the given size.
   */
  public TwoSpace(int size) {
     super(size);
     toSpace = new int[size];     // Create the toSpace heap
  }

  /** Run the garbage collector.
   */
  public void garbageCollect() {
    // Reset the heap allocation pointer to the start of toSpace:
    hp = 0;

    // Forward any objects that are pointed to by a, b, c, or d.
    // These are the "roots" for our garbage collection.
    a = forward(a);
    b = forward(b);
    c = forward(c);
    d = forward(d);

    // Now scavenge the objects that we have already copied over
    // into toSpace and look for reachable objects in the original
    // heap that should also be forwarded into toSpace:
    int toVisit = 0;
    while (toVisit < hp) {
      toVisit += scavenge(toVisit);
    }

    // Now that we have copied all the non-garbage over into toSpace,
    // swap the two heap portions so that further allocation can
    // continue in the toSpace that we've just built without any of
    // the garbage that was lying around in the original heap.  Of
    // course we'll keep the original heap structure around too so
    // that it can be used as our toSpace on the next call to the
    // garbage collector.
    int[] newheap = toSpace;
    toSpace       = heap;
    heap          = newheap;
  }

  /** Forward a reachable object from the current heap into toSpace.
   */
  private int forward(int obj) {
    // There are three cases that you will need to handle here.
    // 1) If obj is not a pointer (i.e., if it is greater than
    //    or equal to zero), then you should just return obj
    //    directly.
    // 2) If obj is a pointer, but the length field of the object
    //    that it points to is negative, then (a) we can conclude
    //    that the object has already been forwarded; and (b) we
    //    can just return the (negative) length value, which is a
    //    pointer to the object's new location in toSpace.
    // 3) If obj is a pointer and the length field is non negative,
    //    then it points to an object that needs to be forwarded to
    //    toSpace.  This requires us to copy the length word and
    //    the associated fields from the heap into the toSpace.
    //    After that, we overwrite the length field with a pointer
    //    to the location of the object in toSpace (because it is
    //    a pointer, this will be a negative number).
    // The description here is longer than my code!

    // delete the following line (and also this commment!):
    fatal("forward has not been implemented!");

    return obj;
  }

  /** Scavenge an object in toSpace for pointers to reachable objects
   *  that are still in the current heap.  Return the total number of
   *  words that are used to represent the object, which is just the
   *  total number of fields plus one (for the length field at the
   *  start of the object).
   */
  private int scavenge(int obj) {
    int len = toSpace[obj];
    // Scan the fields in this object, using forward on
    // any pointer fields that we find to make sure the
    // objects that they refer to are copied into toSpace.

    // delete the following line (and also this commment!):
    fatal("scavenge() has not been implemented yet!");

    return 1+len;
  }
}
