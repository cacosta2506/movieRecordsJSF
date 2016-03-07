/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.movierecordsjsf.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cacosta
 */
@Entity
@Table(name = "movie_records")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieRecords.findAll", query = "SELECT m FROM MovieRecords m"),
    @NamedQuery(name = "MovieRecords.findById", query = "SELECT m FROM MovieRecords m WHERE m.id = :id"),
    @NamedQuery(name = "MovieRecords.findByTitle", query = "SELECT m FROM MovieRecords m WHERE m.title = :title"),
    @NamedQuery(name = "MovieRecords.findByDescription", query = "SELECT m FROM MovieRecords m WHERE m.description = :description"),
    @NamedQuery(name = "MovieRecords.findBySize", query = "SELECT m FROM MovieRecords m WHERE m.size = :size"),
    @NamedQuery(name = "MovieRecords.findByRating", query = "SELECT m FROM MovieRecords m WHERE m.rating = :rating"),
    @NamedQuery(name = "MovieRecords.findByChecksum", query = "SELECT m FROM MovieRecords m WHERE m.checksum = :checksum"),
    @NamedQuery(name = "MovieRecords.findByStaring", query = "SELECT m FROM MovieRecords m WHERE m.staring = :staring"),
    @NamedQuery(name = "MovieRecords.findByUrlPoster", query = "SELECT m FROM MovieRecords m WHERE m.urlPoster = :urlPoster"),
    @NamedQuery(name = "MovieRecords.findByUrlMovie", query = "SELECT m FROM MovieRecords m WHERE m.urlMovie = :urlMovie")})
public class MovieRecords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 5000)
    @Column(name = "description")
    private String description;
    @Column(name = "size")
    private Integer size;
    @Column(name = "rating")
    private Integer rating;
    @Size(max = 500)
    @Column(name = "checksum")
    private String checksum;
    @Size(max = 500)
    @Column(name = "staring")
    private String staring;
    @Size(max = 1000)
    @Column(name = "url_poster")
    private String urlPoster;
    @Size(max = 1000)
    @Column(name = "url_movie")
    private String urlMovie;

    public MovieRecords() {
    }

    public MovieRecords(Integer id) {
        this.id = id;
    }

    public MovieRecords(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getStaring() {
        return staring;
    }

    public void setStaring(String staring) {
        this.staring = staring;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getUrlMovie() {
        return urlMovie;
    }

    public void setUrlMovie(String urlMovie) {
        this.urlMovie = urlMovie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieRecords)) {
            return false;
        }
        MovieRecords other = (MovieRecords) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.movierecordsjsf.MovieRecords[ id=" + id + " ]";
    }
    
}
