package br.com.paixaopet.core.business.entities;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private UUID identifier = randomUUID();
    private String name;
    private String profilePhoto;
    @ElementCollection
    private Set<String> photos;
    private Specie specie;
    private Gender gender;
    private Boolean castrated;
    private LocalDate birthDate;
    private Float approximateAge;
    @ElementCollection
    private Set<String> specialCares;

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Set<String> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<String> photos) {
        this.photos = photos;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getCastrated() {
        return castrated;
    }

    public void setCastrated(Boolean castrated) {
        this.castrated = castrated;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Float getApproximateAge() {
        return approximateAge;
    }

    public void setApproximateAge(Float approximateAge) {
        this.approximateAge = approximateAge;
    }

    public Set<String> getSpecialCares() {
        return specialCares;
    }

    public void setSpecialCares(Set<String> specialCares) {
        this.specialCares = specialCares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(identifier, pet.identifier) && Objects.equals(name, pet.name) && Objects.equals(profilePhoto, pet.profilePhoto) && Objects.equals(photos, pet.photos) && specie == pet.specie && gender == pet.gender && Objects.equals(castrated, pet.castrated) && Objects.equals(birthDate, pet.birthDate) && Objects.equals(approximateAge, pet.approximateAge) && Objects.equals(specialCares, pet.specialCares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier, name, profilePhoto, photos, specie, gender, castrated, birthDate, approximateAge, specialCares);
    }
}
