package dx.battle;

import java.util.TreeMap;
import java.util.TreeSet;

class Memory implements Comparable<Memory> {
    int startAddress;
    int size;

    public Memory(int startAddress, int size) {
        this.startAddress = startAddress;
        this.size = size;
    }

    @Override
    public int compareTo(Memory memory) {
        if (this.size == memory.size) {
            return startAddress - memory.startAddress;
        }
        return size - memory.size;
    }
}

public class MemorySystem {
    private TreeSet<Memory> freeMemorySet;
    private TreeMap<Integer, Integer> allocatedMemoryMap;
    private int memorySize;

    public void init(int N) {
        freeMemorySet = new TreeSet<>();
        allocatedMemoryMap = new TreeMap<>();
        memorySize = N;

        freeMemorySet.add(new Memory(0, N));
    }

    public int allocate(int mSize) {
        Memory memoryForAllocate = freeMemorySet.higher(new Memory(-1, mSize));
        if (memoryForAllocate == null) {
            return -1;
        }
        freeMemorySet.remove(memoryForAllocate);
        if (memoryForAllocate.size > mSize) {
            freeMemorySet.add(new Memory(memoryForAllocate.startAddress + mSize, memoryForAllocate.size - mSize));
        }
        allocatedMemoryMap.put(memoryForAllocate.startAddress, mSize);
        return memoryForAllocate.startAddress;
    }

    public int release(int mAddr) {
        if (!allocatedMemoryMap.containsKey(mAddr)) {
            return -1;
        }
        int freeSize = allocatedMemoryMap.get(mAddr);
        Memory freeMemory = new Memory(mAddr, freeSize);

        if (allocatedMemoryMap.lowerKey(mAddr) != null) {
            int prevAddress = allocatedMemoryMap.lowerKey(mAddr);
            if (prevAddress + allocatedMemoryMap.get(prevAddress) < mAddr) {
                int prevFreeAddress = prevAddress + allocatedMemoryMap.get(prevAddress);
                int prevFreeSize = mAddr - (prevAddress + allocatedMemoryMap.get(prevAddress));
                freeMemory.startAddress = prevFreeAddress;
                freeMemory.size += prevFreeSize;
                freeMemorySet.remove(new Memory(prevFreeAddress, prevFreeSize));
            }
        } else if (mAddr > 0){
            freeMemory.startAddress = 0;
            freeMemory.size += mAddr;
            freeMemorySet.remove(new Memory(0, mAddr));
        }

        if (allocatedMemoryMap.higherKey(mAddr) != null) {
            int nextAddress = allocatedMemoryMap.higherKey(mAddr);
            if (nextAddress > mAddr + freeSize) {
                int nextFreeAddress = mAddr + freeSize;
                int nextFreeSize = nextAddress - (mAddr + freeSize);
                freeMemory.size += nextFreeSize;
                freeMemorySet.remove(new Memory(nextFreeAddress, nextFreeSize));
            }
        } else if(mAddr + freeSize < memorySize){
            freeMemory.size += memorySize - (mAddr + freeSize);
            freeMemorySet.remove(new Memory(mAddr + freeSize, memorySize - (mAddr + freeSize)));
        }

        freeMemorySet.add(freeMemory);
        allocatedMemoryMap.remove(mAddr);
        return freeSize;
    }
}