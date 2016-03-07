/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.movierecordsjsf.controller;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.test.movierecordsjsf.facade.MovieRecordsFacade;
import com.test.movierecordsjsf.model.MovieRecords;
import com.test.movierecordsjsf.util.FileUtil;
import com.test.movierecordsjsf.util.JsfUtil;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author cacosta
 */
@ManagedBean(name = "movieRecordsController")
@ViewScoped
public class MovieRecordsController implements Serializable {

    @EJB
    MovieRecordsFacade movieRecordFacade;

    private List<MovieRecords> listMovieRecords = new ArrayList<>();
    private UploadedFile file;
    private UploadedFile fileMovie;
    private UploadedFile filePoster;
    private MovieRecords movieRecords = new MovieRecords();
    private String pathPoster = "\\C:\\wamp\\www\\images";
    private String pathMovie = "\\C:\\wamp\\www\\trailers";

    public MovieRecordsController() {

    }

    @PostConstruct
    private void upload() {
        listMovie();
    }

    public void listMovie() {

        listMovieRecords = movieRecordFacade.findAll();
    }

    public void uploadXML() {
        if (file != null) {

            try {
                InputStream is = file.getInputstream();

                byte[] contents = IOUtils.toByteArray(is);
                is.close();
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(contents));

                NodeList nlist = doc.getElementsByTagName("Movie");

                for (int i = 0; i < nlist.getLength(); i++) {
                    Node nod = nlist.item(i);
                    MovieRecords movieRecord = new MovieRecords();
                    NodeList dataMovie = nod.getChildNodes();
                    for (int j = 0; j < dataMovie.getLength(); j++) {

                        Node data = dataMovie.item(j);
                        if (data.getNodeType() == Node.ELEMENT_NODE) {
                            if (null != data.getNodeName()) //Show data type
                            {
                                switch (data.getNodeName()) {
                                    case "title": {
                                        //The value is contained in a child node Element
                                        Node dataContect = data.getFirstChild();
                                        //Show the value in the node to be of type Text
                                        if (dataContect != null && dataContect.getNodeType() == Node.TEXT_NODE) {
                                            movieRecord.setTitle(dataContect.getNodeValue());

                                        }
                                        break;
                                    }
                                    case "description": {
                                        //The value is contained in a child node Element
                                        Node dataContect = data.getFirstChild();
                                        //Show the value in the node to be of type Text
                                        if (dataContect != null && dataContect.getNodeType() == Node.TEXT_NODE) {
                                            movieRecord.setDescription(dataContect.getNodeValue());
                                        }
                                        break;
                                    }
                                    case "size": {
                                        System.out.print(data.getNodeName() + ": ");
                                        //The value is contained in a child node Element
                                        Node dataContect = data.getFirstChild();
                                        //Show the value in the node to be of type Text
                                        if (dataContect != null && dataContect.getNodeType() == Node.TEXT_NODE) {
                                            movieRecord.setSize(Integer.parseInt(dataContect.getNodeValue()));
                                        }
                                        break;
                                    }
                                    case "rating": {

                                        //The value is contained in a child node Element
                                        Node dataContect = data.getFirstChild();
                                        //Show the value in the node to be of type Text
                                        if (dataContect != null && dataContect.getNodeType() == Node.TEXT_NODE) {
                                            movieRecord.setRating(Integer.parseInt(dataContect.getNodeValue()));

                                        }
                                        break;
                                    }
                                    case "checksum": {

                                        //The value is contained in a child node Element
                                        Node dataContect = data.getFirstChild();
                                        //Show the value in the node to be of type Text
                                        if (dataContect != null && dataContect.getNodeType() == Node.TEXT_NODE) {
                                            movieRecord.setChecksum(dataContect.getNodeValue());

                                        }
                                        break;
                                    }
                                    case "staring": {

                                        //The value is contained in a child node Element
                                        Node dataContect = data.getFirstChild();
                                        //Show the value in the node to be of type Text
                                        if (dataContect != null && dataContect.getNodeType() == Node.TEXT_NODE) {
                                            movieRecord.setStaring(dataContect.getNodeValue());

                                        }
                                        break;
                                    }
                                    default:
                                        break;
                                }
                            }
                        }

                    }
                    movieRecordFacade.create(movieRecord);

                }
                listMovie();

            } catch (Exception ex) {
                Logger.getLogger(MovieRecordsController.class.getName()).log(Level.SEVERE, null, ex);

            }

            JsfUtil.msgInfo(file.getFileName() + " successfully uploaded.");

        }
    }

    public String getTagValue(String nom, Element e) {
        NodeList lst = e.getElementsByTagName(nom).item(0).getChildNodes();
        Node n = (Node) lst.item(0);
        return n.getNodeValue();
    }

    public void uploadPoster() {
        fileMovie = null;
        file = null;
        if (filePoster != null) {
            try {
                InputStream is = filePoster.getInputstream();
                byte[] contents = IOUtils.toByteArray(is);
                is.close();

                boolean createfile = FileUtil.writeFile(filePoster.getFileName(), contents, pathPoster);
                if (createfile) {
                    movieRecords.setUrlPoster(filePoster.getFileName());
                    movieRecordFacade.edit(movieRecords);
                    listMovie();
                    JsfUtil.msgInfo("Poster successfully uploaded.");
                }
            } catch (Exception ex) {
                Logger.getLogger(MovieRecordsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void sendMovie(MovieRecords movie) {

        movieRecords = movie;

    }

    public void deleteMovie() {
        String sourcePoster = pathPoster + "\\" + movieRecords.getUrlPoster();
        String sourceMovie = pathMovie + "\\" + movieRecords.getUrlMovie();
        MovieRecords movieRecordsAux = this.movieRecords;
        movieRecordFacade.remove(movieRecords);
        if (null != movieRecordsAux.getUrlPoster()) {
            FileUtil.deleteMovie(sourcePoster);
        }

        if (null != movieRecordsAux.getUrlMovie()) {
            FileUtil.deleteMovie(sourceMovie);
        }
        listMovie();
        JsfUtil.msgInfo("Record successfully deleted");

    }

    public void uploadMovie() {
        file = null;
        filePoster = null;
        if (fileMovie != null) {
            try {
                InputStream is = fileMovie.getInputstream();
                byte[] contents = IOUtils.toByteArray(is);
                is.close();

                String checksum = getMD5Checksum();

                if (checksum == null ? movieRecords.getChecksum() == null : checksum.equals(movieRecords.getChecksum())) {

                    boolean createfile = FileUtil.writeFile(fileMovie.getFileName(), contents, pathMovie);
                    if (createfile) {
                        movieRecords.setUrlMovie(fileMovie.getFileName());
                        movieRecordFacade.edit(movieRecords);
                        listMovie();
                        JsfUtil.msgInfo("Movie successfully uploaded.");
                    }
                } else {
                    JsfUtil.msgError("The checksum doesn't match");
                }

            } catch (Exception ex) {
                Logger.getLogger(MovieRecordsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public String getMD5Checksum() {

        byte[] textBytes = new byte[1024];
        MessageDigest md = null;
        int read = 0;
        String md5 = null;
        try {
            InputStream is = fileMovie.getInputstream();
            md = MessageDigest.getInstance("MD5");
            while ((read = is.read(textBytes)) > 0) {
                md.update(textBytes, 0, read);
            }
            is.close();
            byte[] md5sum = md.digest();
            md5 = toHexadecimal(md5sum);
        } catch (FileNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        } catch (IOException e) {

        }

        return md5;

    }

    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<MovieRecords> getListMovieRecords() {
        return listMovieRecords;
    }

    public void setListMovieRecords(List<MovieRecords> listMovieRecords) {
        this.listMovieRecords = listMovieRecords;
    }

    public String getPathPoster() {
        return pathPoster;
    }

    public void setPathPoster(String pathPoster) {
        this.pathPoster = pathPoster;
    }

    public UploadedFile getFileMovie() {
        return fileMovie;
    }

    public void setFileMovie(UploadedFile fileMovie) {
        this.fileMovie = fileMovie;
    }

    public String getPathMovie() {
        return pathMovie;
    }

    public void setPathMovie(String pathMovie) {
        this.pathMovie = pathMovie;
    }

    public MovieRecords getMovieRecords() {
        return movieRecords;
    }

    public void setMovieRecords(MovieRecords movieRecords) {
        this.movieRecords = movieRecords;
    }

    public UploadedFile getFilePoster() {
        return filePoster;
    }

    public void setFilePoster(UploadedFile filePoster) {
        this.filePoster = filePoster;
    }

}
