public class RabbitMale extends Rabbit
{
    /**
     * Constructeur de la classe RabbitMale
     */
    public RabbitMale ()
    {
        super();
    }

    /**
     * Autre constructeur pour la classe RabbitMale
     *
     * @param age                   Âge du lapin
     * @param sexualMaturityAge     Âge de maturité sexuelle du lapin
     */
    public RabbitMale (int age, int sexualMaturityAge)
    {
        super(age, sexualMaturityAge);
    }

    /**
     * Retourne le genre du lapin.
     *
     * @return Le genre du lapin
     */
    @Override
    public Gender getGender() {
        return Gender.MALE;
    }
}
