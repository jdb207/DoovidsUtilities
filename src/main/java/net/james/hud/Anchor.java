package net.james.hud;

public enum Anchor {

    TOP_LEFT {
        @Override
        public int calculateX(int screenWidth, int elementWidth, int offsetX) {
            return offsetX;
        }

        @Override
        public int calculateY(int screenHeight, int elementHeight, int offsetY) {
            return offsetY;
        }

        @Override
        public int calculateOffsetX(int screenWidth, int elementWidth, int x) {
            return x;
        }

        @Override
        public int calculateOffsetY(int screenHeight, int elementHeight, int y) {
            return y;
        }
    },

    TOP_RIGHT {
        @Override
        public int calculateX(int screenWidth, int elementWidth, int offsetX) {
            return screenWidth - elementWidth + offsetX;
        }

        @Override
        public int calculateY(int screenHeight, int elementHeight, int offsetY) {
            return offsetY;
        }

        @Override
        public int calculateOffsetX(int screenWidth, int elementWidth, int x) {
            return x - (screenWidth - elementWidth  );
        }

        @Override
        public int calculateOffsetY(int screenHeight, int elementWidth, int y) {
            return y;
        }
    },

    BOTTOM_LEFT {
        @Override
        public int calculateX(int screenWidth, int elementWidth, int offsetX) {
            return offsetX;
        }

        @Override
        public int calculateY(int screenHeight, int elementHeight, int offsetY) {
            return screenHeight - elementHeight + offsetY;
        }

        @Override
        public int calculateOffsetX(int screenWidth, int elementWidth, int x) {
            return x;
        }

        @Override
        public int calculateOffsetY(int screenHeight, int elementHeight, int y) {
            return y - (screenHeight - elementHeight);
        }
    },

    BOTTOM_RIGHT {
        @Override
        public int calculateX(int screenWidth, int elementWidth, int offsetX) {
            return screenWidth - elementWidth + offsetX;
        }

        @Override
        public int calculateY(int screenHeight, int elementHeight, int offsetY) {
            return screenHeight - elementHeight + offsetY;
        }

        @Override
        public int calculateOffsetX(int screenWidth, int elementWidth, int x) {
            return x - (screenWidth - elementWidth);
        }

        @Override
        public int calculateOffsetY(int screenHeight, int elementHeight, int y) {
            return y - (screenHeight - elementHeight);
        }
    };

    public abstract int calculateX(int screenWidth, int elementWidth, int offsetX);

    public abstract int calculateY(int screenHeight, int elementHeight, int offsetY);

    public abstract int calculateOffsetX(int screenWidth, int elementWidth, int x);

    public abstract int calculateOffsetY(int screenHeight, int elementHeight, int y);

    public static Anchor fromPosition(int screenWidth, int screenHeight, int x, int y) {
        boolean left = x < screenWidth / 2;
        boolean top = y < screenHeight / 2;

        if (left && top) {
            return TOP_LEFT;
        }

        if (!left && top) {
            return TOP_RIGHT;
        }

        if (left) {
            return BOTTOM_LEFT;
        }

        return BOTTOM_RIGHT;
    }
}
