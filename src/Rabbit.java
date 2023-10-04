public abstract class Rabbit
{
    enum Gender
    {
        MALE,
        FEMALE
    }

    protected       int     age;                        // En mois
    protected final int     sexualMaturityAge;          // En mois
    protected       boolean isDead;

    /**
     * Constructeur de la classe Rabbit
     */
    public Rabbit ()
    {
        this.age = 0;
        this.sexualMaturityAge = (int) MersenneTwister.uniform(5, 9);   // [5, 6, 7, 8]
        this.isDead = false;

        this.potentialDeath();
    }

    /**
     * Autre constructeur pour la classe Rabbit
     *
     * @param age                   Âge du lapin
     * @param sexualMaturityAge     Âge de maturité sexuelle du lapin
     */
    public Rabbit (int age, int sexualMaturityAge)
    {
        this.age = age;
        this.sexualMaturityAge = sexualMaturityAge;
        this.isDead = false;

        this.potentialDeath();
    }

    /**
     * Retourne le genre du lapin.
     *
     * @return Le genre du lapin
     */
    public abstract Gender getGender ();

    /**
     * Retourne si le lapin est mort ou non.
     *
     * @return  Si le lapin est mort : <b>true</b>
     *          Sinon : <b>false</b>
     */
    public boolean isDead () {
        return this.isDead;
    }

    /**
     * Retourne si le lapin est mature sexuellement ou non.
     *
     * @return  Si le lapin est mature sexuellement : true
     *          Sinon : false
     */
    public boolean isSexuallyMature ()
    {
        return (this.age >= this.sexualMaturityAge);
    }

    /**
     * Ajoute un mois au lapin et déclenche les fonctions mensuelles.
     */
    public void newMonth ()
    {
        this.age++;

        // Tirage pour savoir si le lapin meurt ou non
        this.potentialDeath();
    }

    /**
     * Appelle ou non la méthode de mort du lapin selon les probabilitées définies.
     */
    private void potentialDeath ()
    {
        double randomNb = MersenneTwister.genrand_real1();

        if (! this.isSexuallyMature())
        {
            // Non mature sexuellement
            if (randomNb > Math.pow(0.5, (1 / 12.0)))
            {
                this.kill();
            }
        }
        else if ((this.age / 12) < 7)
        {
            // Moins de 7 ans et mature sexuellement
            if (randomNb > Math.pow(0.75, (1 / 12.0)))
            {
                this.kill();
            }
        }
        else
        {
            // 7 ans ou plus

            // Calcul de la probabilité de survie selon l'âge du lapin
            double survivalProbability = ((float)((75 - (15 * ((this.age / 12) - 7)))) / 100);

            // Si la probabilité de mourir est de 100% alors le lapin meurt
            // Sinon, on effectue un tirage avec la probabilité calculée
            if (survivalProbability == 0) {
                this.kill();
            }
            else if (randomNb > Math.pow(survivalProbability, (1 / 12.0)))
            {
                this.kill();
            }
        }
    }

    /**
     * Fait mourir le lapin.
     */
    protected void kill ()
    {
        this.isDead = true;
    }
}
