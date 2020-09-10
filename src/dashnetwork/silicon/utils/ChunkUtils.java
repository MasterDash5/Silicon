package dashnetwork.silicon.utils;

public class ChunkUtils {

    public static int getSection(int bit) {
        int count = 0;

        for (int i = 0; i < 16; i++)
            if ((bit & 1 << i) != 0)
                count++;

        return count;
    }

}
