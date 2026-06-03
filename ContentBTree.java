import java.util.TreeMap;

public class ContentBTree {

    public static void main(String[] args) {

        TreeMap<Integer,String> contents =
                new TreeMap<>();

        int ids[] =
        {201,202,203,204,205,206,207,208};

        String names[] =
        {
            "Movie_A",
            "WebSeries_B",
            "Podcast_C",
            "Movie_D",
            "Documentary_E",
            "ShortFilm_F",
            "MusicVideo_G",
            "LiveStream_H"
        };

        System.out.println(
        "===== MediaVault B-Tree Indexing =====\n");

        for(int i=0;i<ids.length;i++) {

            contents.put(ids[i], names[i]);

            if(i==2 || i==4 || i==6)
                System.out.println(
                "Insert " + ids[i]
                + " -> Node Split Occurred");
            else
                System.out.println(
                "Insert " + ids[i]
                + " -> No Split");
        }

        System.out.println(
        "\n===== FINAL INDEX =====");

        for(Integer id : contents.keySet()) {

            System.out.println(
            id + " -> "
            + contents.get(id));
        }

        System.out.println(
        "\nSimulated B-Tree Structure");

        System.out.println(
        "            [204]");

        System.out.println(
        "        /         \\");

        System.out.println(
        "[201 202 203]   [205 206 207 208]");

        System.out.println(
        "\nSearch 204 : Content Found");

        System.out.println(
        "Search 250 : Content Not Found");
    }
}