public class RabbitFemale extends Rabbit
{
    protected boolean   isFertile;
    protected int[]     tabLitters;

    /**
     * Constructeur de la classe RabbitFemale
     */
    public RabbitFemale ()
    {
        super();

        this.isFertile = true;
        this.tabLitters = new int[12];

        this.potentialSterilization();
        this.birthday();
    }

    /**
     * Autre constructeur pour la classe RabbitFemale
     *
     * @param age                   Âge du lapin
     * @param sexualMaturityAge     Âge de maturité sexuelle du lapin
     */
    public RabbitFemale (int age, int sexualMaturityAge)
    {
        super(age, sexualMaturityAge);

        this.isFertile = true;
        this.tabLitters = new int[12];

        this.potentialSterilization();
        this.birthday();
    }

    /**
     * Retourne le genre du lapin.
     *
     * @return Le genre du lapin
     */
    @Override
    public Gender getGender() {
        return Gender.FEMALE;
    }

    /**
     * Retourne si la lapine est fertile ou non.
     *
     * @return  Si le lapin est fertile : <b>true</b>
     *          Sinon : <b>false</b>
     */
    public boolean isFertile ()
    {
        return this.isFertile;
    }

    /**
     * Rend la lapine stérile.
     */
    public void makeSterile ()
    {
        this.isFertile = false;
    }

    /**
     * Retourne si le mois courant est un mois de portée ou non.
     *
     * @return  Si le mois courant est un mois de portée : <b>true</b>
     *          Sinon : <b>false</b>
     */
    public boolean isAMonthOfLitter ()
    {
        return (this.tabLitters[this.age % 12] == 1);
    }

    /**
     * Retourne le nombre de portée de l'année courante.
     *
     * @return  Le nombre de portée de l'année courante
     */
    public int getNbOfLitterOfTheYear ()
    {
        int nbOfLitterOfTheYear = 0;

        for (int i = 0; i < 12; i++)
        {
            if (this.tabLitters[i] == 1)
            {
                nbOfLitterOfTheYear++;
            }
        }

        return nbOfLitterOfTheYear;
    }

    /**
     * Déclenche les fonctions mensuelles sur la lapine.
     */
    @Override
    public void newMonth() {
        super.newMonth();

        this.potentialSterilization();

        if ((this.age % 12) == 0)
        {
            this.birthday();
        }
    }

    /**
     * Déclenche les fonctions annuelles sur la lapine.
     */
    protected void birthday() {
        // Si la lapine est fertile, on définit les mois où elle accouchera
        if (this.isFertile) {
            this.fillInTabLitters();
        }
    }

    /**
     * Appelle ou non la méthode de stérilisation de la lapine selon la probabilitée définie.
     */
    public void potentialSterilization()
    {
        // La lapine a 10% de chance de devenir stérile chaque année
        double randomNb = MersenneTwister.genrand_real1();
        if (randomNb > Math.pow(0.9, (1 / 12.0)))
        {
            this.makeSterile();
        }
    }

    /**
     * Tire les mois de l'année où la lapine accouchera.
     */
    public void fillInTabLitters ()
    {
        // Reinitialisation du tableau des portées
        for (int i = 0; i < 12; i++)
        {
            this.tabLitters[i] = 0;
        }

        /* ----------- Tirage du nombre de portée de l'année ----------- */
        int     nbLitters,
                freeMonths = 12;

        double randomNb = MersenneTwister.genrand_real1();

        if (randomNb < 0.05)
        {
            nbLitters = 3; // 5 %
        }
        else if (randomNb < 0.15)
        {
            nbLitters = 4; // 10 %
        }
        else if (randomNb < 0.35)
        {
            nbLitters = 5; // 20 %
        }
        else if (randomNb < 0.65)
        {
            nbLitters = 6; // 30 %
        }
        else if (randomNb < 0.85)
        {
            nbLitters = 7; // 20 %
        }
        else if (randomNb < 0.95)
        {
            nbLitters = 8; // 10 %
        }
        else
        {
            nbLitters = 9; // 5 %
        }

        // Pour chaque portée...
        while (nbLitters > 0)
        {
            // On designe au hasard parmis les mois sans portée une nouvelle portée.
            int pos = (int) MersenneTwister.uniform(0, freeMonths);

            for (int j = 0; j < 12; j++)
            {
                if (this.tabLitters[j] == 0)
                {
                    if (pos == 0) {
                        this.tabLitters[j] = 1;
                        break;
                    }
                    else
                    {
                        pos--;
                    }
                }
            }

            freeMonths--;
            nbLitters--;
        }
    }

    /**
     * Fait accoucher les lapines qui ont atteint leur maturité sexuelle, qui sont fertiles et qui doivent accoucher le mois courant
     *
     * @return  Un tableau de Rabbit qui sont les nouveaux nés
     */
    public Rabbit[] giveBirth ()
    {
        // Si la lapine est sexuellement mature ET fertile ET que le mois courant est un mois de portée...
        if (this.isSexuallyMature() && this.isFertile() && this.isAMonthOfLitter())
        {

            double randomNb;

            /* ----------- Tirage du nombre de petit de la portée ----------- */

            // Même chance d'obtenir 3 à 5 petits et un peu moins d'en obtenir 2 ou 6.
            int nbNewBorns;

            randomNb = MersenneTwister.genrand_real1();
            if (randomNb < 0.17)
            {
                nbNewBorns = 2; // 17 %
            }
            else if (randomNb < 0.39)
            {
                nbNewBorns = 3; // 22 %
            }
            else if (randomNb < 0.61)
            {
                nbNewBorns = 4; // 22 %
            }
            else if (randomNb < 0.83)
            {
                nbNewBorns = 5; // 22 %
            }
            else
            {
                nbNewBorns = 6; // 17 %
            }


            /* ----------- Naissance des petits ----------- */

            Rabbit[] kittens = new Rabbit[nbNewBorns];

            for (int i = 0; i < nbNewBorns; i++)
            {
                Rabbit kitten;

                // 50/50 d'être fille ou garcon.
                randomNb = MersenneTwister.genrand_real1();
                if (randomNb < 0.5)
                {
                    kitten = new RabbitFemale();
                }
                else
                {
                    kitten = new RabbitMale();
                }

                kittens[i] = kitten;
            }

            // 15% de chance de mourir sur l'année à cause d'un accouchement.
            randomNb = MersenneTwister.genrand_real1();
            if (randomNb > Math.pow(0.85, (1 / ((double) this.getNbOfLitterOfTheYear()))))
            {
                this.kill();
            }

            return kittens;
        }
        else
        {
            // Si la lapine n'accouche pas, on retourne null.
            return null;
        }
    }
}
