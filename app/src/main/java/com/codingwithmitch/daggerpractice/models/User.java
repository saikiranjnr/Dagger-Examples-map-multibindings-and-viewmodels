package com.codingwithmitch.daggerpractice.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class User extends BaseObservable {

    private String website;

    private String inputId;

    private Address address;

    private String phone;

    private String name;

    private Company company;

    private int id;

    private String email;

    private String username;


    public String getWebsite ()
    {
        return website;
    }

    public void setWebsite (String website)
    {
        this.website = website;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress (Address address)
    {
        this.address = address;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Company getCompany ()
    {
        return company;
    }

    public void setCompany (Company company)
    {
        this.company = company;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    class Company
    {
        private String bs;

        private String catchPhrase;

        private String name;

        public String getBs ()
        {
            return bs;
        }

        public void setBs (String bs)
        {
            this.bs = bs;
        }

        public String getCatchPhrase ()
        {
            return catchPhrase;
        }

        public void setCatchPhrase (String catchPhrase)
        {
            this.catchPhrase = catchPhrase;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [bs = "+bs+", catchPhrase = "+catchPhrase+", name = "+name+"]";
        }
    }

    class Address
    {
        private String zipcode;

        private Geo geo;

        private String suite;

        private String city;

        private String street;

        public String getZipcode ()
        {
            return zipcode;
        }

        public void setZipcode (String zipcode)
        {
            this.zipcode = zipcode;
        }

        public Geo getGeo ()
        {
            return geo;
        }

        public void setGeo (Geo geo)
        {
            this.geo = geo;
        }

        public String getSuite ()
        {
            return suite;
        }

        public void setSuite (String suite)
        {
            this.suite = suite;
        }

        public String getCity ()
        {
            return city;
        }

        public void setCity (String city)
        {
            this.city = city;
        }

        public String getStreet ()
        {
            return street;
        }

        public void setStreet (String street)
        {
            this.street = street;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [zipcode = "+zipcode+", geo = "+geo+", suite = "+suite+", city = "+city+", street = "+street+"]";
        }
    }

    class Geo
    {
        private String lng;

        private String lat;

        public String getLng ()
        {
            return lng;
        }

        public void setLng (String lng)
        {
            this.lng = lng;
        }

        public String getLat ()
        {
            return lat;
        }

        public void setLat (String lat)
        {
            this.lat = lat;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [lng = "+lng+", lat = "+lat+"]";
        }
    }



}
