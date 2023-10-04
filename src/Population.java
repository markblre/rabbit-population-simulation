import java.util.ArrayList;
import java.util.Arrays;

public class Population
{
    private final ArrayList<Rabbit> rabbits;

    /**
     * Constructeur de la classe Population.
     *
     * @param   rabbits Les lapins initiaux de la population
     */
    public Population (Rabbit... rabbits)
    {
        this.rabbits = new ArrayList<>();
        this.rabbits.addAll(Arrays.asList(rabbits));
    }

    /**
     * Retourne le nombre de lapin de la population.
     *
     * @return     Le nombre de lapin présent dans la population
     */
    public int nbRabbit ()
    {
        return rabbits.size();
    }

    /**
     * Déclenche les fonctions mensuelles sur la population.
     */
    public void newMonth ()
    {
        // Pour chaque lapin de la population on appelle sa fonction newMonth()
        for (Rabbit rabbit : rabbits) {
            rabbit.newMonth();
        }

        // Suppression de tout les lapins morts de la population
        this.removeDeadRabbits();

        // Déclenchement des accouchements des lapines
        this.triggeringBirths();
    }

    /**
     * Retourne s'il reste des lapins du genre passé en paramètre dans la population ou non.
     *
     * @param  gender  Genre que l'on souhaite tester
     *
     * @return         Si la population contient des lapins du genre donné en paramètre : <b>true</b>
     *                  Sinon : <b>false</b>
     */
    public boolean hasGender (Rabbit.Gender gender)
    {
        // Pour chaque lapin de la population...
        for (Rabbit rabbit : rabbits)
        {
            // s'il est du genre voulu on retourne true.
            if (rabbit.getGender() == gender)
            {
                return true;
            }
        }

        // sinon on retourne false
        return false;
    }

    /**
     * Déclenche les accouchements des lapins.
     */
    public void triggeringBirths ()
    {
        // Création d'une liste vide
        // C'est une liste qui stockera temporairement toute les naissances du mois
        ArrayList<Rabbit> newBorns = new ArrayList<>();

        // Pour chaque lapin de la population...
        for (Rabbit rabbit : rabbits)
        {
            // si c'est une femelle...
            if (rabbit.getGender() == Rabbit.Gender.FEMALE) {

                // Stockage dans un tableau des naissances de la lapine
                Rabbit[] kittens = ((RabbitFemale) rabbit).giveBirth();

                // S'il y a eu des petits...
                if (kittens != null) {
                    // Ajout des petits à la liste newBorns
                    newBorns.addAll(Arrays.asList(kittens));
                }
            }
        }

        // Ajout de tout les nouveaux nés dans la population
        this.rabbits.addAll(newBorns);

        // Suppression des lapines mortes durant leur accouchement
        this.removeDeadRabbits();
    }

    /**
     * Supprime tout les lapins morts de la population.
     */
    private void removeDeadRabbits ()
    {
        // Supprime tout les lapins de la liste de lapins qui ont l'attribut isDead == true
        rabbits.removeIf(Rabbit::isDead);
    }


}
