public abstract class Direction implements Movable{

    protected double m_xpos = 0;
    protected double m_ypos = 0;
    protected double m_directon = Math.toRadians(90);

    double speed;

    public void move() {
        m_ypos = m_ypos + speed*Math.round(Math.sin(m_directon));
        m_xpos = m_xpos + speed*Math.round(Math.cos(m_directon));
    }
    public void turnLeft() {
        m_directon = m_directon - Math.toRadians(90);
    }
    public void turnRight() {
        m_directon = m_directon + Math.toRadians(90);
    }
    public String get_pos() {return  "Y position: " + m_ypos + "\n" + "X position: " + m_xpos; }

}
