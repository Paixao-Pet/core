package br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests;

import br.com.paixaopet.core.business.entities.Gender;
import br.com.paixaopet.core.business.entities.Specie;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CreatePetRequest {

    private String name;
    private String profilePhoto;
    private List<String> photos;
    private Specie specie;
    private Gender gender;
    private Boolean castrated;
    private LocalDate birthDate;
    private Integer approximateAge;
    private List<String> specialCares;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePetRequest that = (CreatePetRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(profilePhoto, that.profilePhoto) && Objects.equals(photos, that.photos) && specie == that.specie && gender == that.gender && Objects.equals(castrated, that.castrated) && Objects.equals(birthDate, that.birthDate) && Objects.equals(approximateAge, that.approximateAge) && Objects.equals(specialCares, that.specialCares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, profilePhoto, photos, specie, gender, castrated, birthDate, approximateAge, specialCares);
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

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
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

    public Integer getApproximateAge() {
        return approximateAge;
    }

    public void setApproximateAge(Integer approximateAge) {
        this.approximateAge = approximateAge;
    }

    public List<String> getSpecialCares() {
        return specialCares;
    }

    public void setSpecialCares(List<String> specialCares) {
        this.specialCares = specialCares;
    }
}
