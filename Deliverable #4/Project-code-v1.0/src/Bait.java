class Bait {
    private String name;
    private float price;
    private int effectiveness;

    public Bait(String name, float price, int effectiveness) {
        this.name = name;
        this.price = price;
        this.effectiveness = effectiveness;
    }

    // Getters
    public String getName() { return name; }
    public float getPrice() { return price; }
    public int getEffectiveness() { return effectiveness; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(float price) { this.price = price; }
    public void setEffectiveness(int effectiveness) { this.effectiveness = effectiveness; }

    @Override
    public String toString() {
        return String.format("Bait{name='%s', price=%.2f, effectiveness=%d}",
                name, price, effectiveness);
    }
}