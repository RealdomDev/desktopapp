// You can use this class to test the Dot.java class and see how it works
// Let Alex know if you have any questions as to how it works

class Driver {
    private static float[] DEFAULTHASHES = {0, 28, 58, 85};
    private static float[] DEFAULTYARDLS = {0, 8, 16, 24, 32, 40, 48, 56, 64, 72,
    80, 88, 96, 104, 112, 120, 128, 136, 144, 152, 160};
    private static String[] DEFAULTHASHNAMES = {"front sideline", "front hash",
    "back hash", "back sideline"};
    private static String[] DEFAULTYARDNAMES = {"0 yardline side 1",
    "5 yardline side 1", "10 yardline side 1", "15 yardline side 1",
    "20 yardline side 1", "25 yardline side 1", "30 yardline side 1",
    "35 yardline side 1", "40 yardline side 1", "45 yardline side 1",
    "50 yardline", "45 yardline side 2", "40 yardline side 2",
    "35 yardline side 2", "30 yardline side 2", "25 yardline side 2",
    "20 yardline side 2", "15 yardline side 2", "10 yardline side 2",
    "5 yardline side 2", "0 yardline side 2"};

    public static void main(String[] args) {
        Dot d1 = new Dot();
        System.out.println("Begin test:");
        System.out.println(d1.toStringFtB(DEFAULTHASHES, DEFAULTHASHNAMES)); // On front sideline
        System.out.println(d1.toStringLtR(DEFAULTYARDLS, DEFAULTYARDNAMES)); // On the 0 yardline

        d1.setY(28);
        d1.setX(92);
        System.out.println(d1.toStringFtB(DEFAULTHASHES, DEFAULTHASHNAMES)); // On front hash
        System.out.println(d1.toStringLtR(DEFAULTYARDLS, DEFAULTYARDNAMES)); // On the 20 yardline side 1

        d1.setY(96);
        d1.setX(36);
        System.out.println(d1.toStringFtB(DEFAULTHASHES, DEFAULTHASHNAMES)); // 11 steps behind back sideline
        System.out.println(d1.toStringLtR(DEFAULTYARDLS, DEFAULTYARDNAMES)); // 4 steps outside of the 25 yardline side 1

        d1.setY(40);
        d1.setX(80);
        System.out.println(d1.toStringFtB(DEFAULTHASHES, DEFAULTHASHNAMES)); // 12 steps behind back hash
        System.out.println(d1.toStringLtR(DEFAULTYARDLS, DEFAULTYARDNAMES)); // On the 50 yardline

        d1.setY(14);
        d1.setX(87);
        System.out.println(d1.toStringFtB(DEFAULTHASHES, DEFAULTHASHNAMES)); // 14 steps in front of the the front hash
        System.out.println(d1.toStringLtR(DEFAULTYARDLS, DEFAULTYARDNAMES)); // 1 steps inside 45 yardline side 2
    }
}
