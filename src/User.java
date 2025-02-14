public class User {
    private int rank;
    private int progress;

    public User() {
        rank = -8;
        progress = 0;
    }

    public int getRank() {
        return rank;
    }

    public int getProgress() {
        return progress;
    }

    public void incProgress(int activity) {
        if (activity == 0 || activity < -8 || activity > 8) {
            throw new IllegalArgumentException("The rank of an activity cannot be less than 8, 0, or greater than 8!");
        }

        int diff = activity - rank;
        if (activity <= -1 && rank >= 1) {
            diff++;
        } else if (activity >= 1 && rank <= -1) {
            diff--;
        }

        int points = 0;

        if (diff == -1) {
            points = 1;
        } else if (diff == 0) {
            points = 3;
        } else if (diff > 0) {
            points = 10 * diff * diff;
        }

        int rank_inc = (progress + points) / 100;
        if (rank + rank_inc >= 0 && rank <= -1) {
            rank_inc++;
        }

        if (rank + rank_inc > 8) {
            rank = 8;
            progress = 100;
        } else if (rank + rank_inc == 8) {
            rank += rank_inc;
            if ((progress + points) > 100) {
                progress = 100;
            } else {
                progress += points;
            }
        } else {
            rank += rank_inc;
            progress = (progress + points) % 100;
        }
    }

    public String toString() {
        return "User{rank=" + rank + ", progress=" + progress + "}";
    }
}
