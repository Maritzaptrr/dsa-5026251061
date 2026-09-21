package lw01.unguided;

public abstract class Rental implements Chargeable {
    private String id;
    private int days;
    private int units;

    protected Rental(String id, int days) {
        this.id = id;
        this.days = days;

        if (days <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public void setUnits(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException();
        }

        this.units = units;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException();
        }

        return units * calculateCharge();
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge(units);
    }
}