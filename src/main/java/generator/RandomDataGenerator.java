package generator;

import connection.Neo4jConnection;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomDataGenerator {
    private final Connection connection = new Neo4jConnection().getConnection("jdbc:neo4j:bolt://localhost:7687",
            "neo4j", "12345678");
    private ArrayList<String> urls = new ArrayList<String>();
    private ArrayList<String> cities = new ArrayList<String>();
    private ArrayList<String> locations = new ArrayList<String>();
    private ArrayList<String> fullNames = new ArrayList<String>();
    private ArrayList<String> personDescriptions = new ArrayList<String>();
    private ArrayList<String> organizations = new ArrayList<String>();
    private ArrayList<String> organizationDescriptions = new ArrayList<String>();
    private ArrayList<String> countries = new ArrayList<String>();
    private ArrayList<String> capitals = new ArrayList<String>();
    private ArrayList<String> countryDescriptions = new ArrayList<String>();
    private ArrayList<String> locationDescriptions = new ArrayList<String>();
    private ArrayList<String> events = new ArrayList<String>();
    private ArrayList<String> eventDescriptions = new ArrayList<String>();
    private ArrayList<String> agreements = new ArrayList<String>();
    private ArrayList<String> agreementDescriptions = new ArrayList<String>();
    private int personsNumber = 1, organizationsNumber = 1, locationsNumber = 1, countriesNumber = 1;
    private int eventsNumber = 1, timesNumber = 1, agreementsNumber = 1;
    private static Random random = new Random();

    public RandomDataGenerator() {
        try {
            BufferedReader urlFile = new BufferedReader(new FileReader("src/main/resources/url.txt"));
            String url;
            while ((url = urlFile.readLine()) != null) {
                urls.add(url);
            }
            BufferedReader cityFile = new BufferedReader(new FileReader("src/main/resources/city.txt"));
            String city;
            while ((city = cityFile.readLine()) != null) {
                cities.add(city);
            }
            BufferedReader agreementFile = new BufferedReader(new FileReader("src/main/resources/agreement.txt"));
            String agreement;
            while ((agreement = agreementFile.readLine()) != null) {
                agreements.add(agreement);
            }
            BufferedReader agreementDescriptionFile = new BufferedReader(new FileReader("src/main/resources/agreement description.txt"));
            String agreementDescription;
            while ((agreementDescription = agreementDescriptionFile.readLine()) != null) {
                agreementDescriptions.add(agreementDescription);
            }
            BufferedReader capitalFile = new BufferedReader(new FileReader("src/main/resources/capital.txt"));
            String capital;
            while ((capital = capitalFile.readLine()) != null) {
                capitals.add(capital);
            }
            BufferedReader countryFile = new BufferedReader(new FileReader("src/main/resources/country.txt"));
            String country;
            while ((country = countryFile.readLine()) != null) {
                countries.add(country);
            }
            BufferedReader countryDescriptionFile = new BufferedReader(new FileReader("src/main/resources/country description.txt"));
            String countryDescription;
            while ((countryDescription = countryDescriptionFile.readLine()) != null) {
                countryDescriptions.add(countryDescription);
            }
            BufferedReader eventFile = new BufferedReader(new FileReader("src/main/resources/event.txt"));
            String event;
            while ((event = eventFile.readLine()) != null) {
                events.add(event);
            }
            BufferedReader eventDescriptionFile = new BufferedReader(new FileReader("src/main/resources/event description.txt"));
            String eventDescription;
            while ((eventDescription = eventDescriptionFile.readLine()) != null) {
                eventDescriptions.add(eventDescription);
            }
            BufferedReader fullNameFile = new BufferedReader(new FileReader("src/main/resources/full name.txt"));
            String fullName;
            while ((fullName = fullNameFile.readLine()) != null) {
                fullNames.add(fullName);
            }
            BufferedReader locationFile = new BufferedReader(new FileReader("src/main/resources/location.txt"));
            String location;
            while ((location = locationFile.readLine()) != null) {
                locations.add(location);
            }
            BufferedReader locationDescriptionFile = new BufferedReader(new FileReader("src/main/resources/location description.txt"));
            String locationDescription;
            while ((locationDescription = locationDescriptionFile.readLine()) != null) {
                locationDescriptions.add(locationDescription);
            }
            BufferedReader organizationFile = new BufferedReader(new FileReader("src/main/resources/organization.txt"));
            String organization;
            while ((organization = organizationFile.readLine()) != null) {
                organizations.add(organization);
            }
            BufferedReader organizationDescriptionFile = new BufferedReader(new FileReader("src/main/resources/organization description.txt"));
            String organizationDescription;
            while ((organizationDescription = organizationDescriptionFile.readLine()) != null) {
                organizationDescriptions.add(organizationDescription);
            }
            BufferedReader personDescriptionFile = new BufferedReader(new FileReader("src/main/resources/person description.txt"));
            String personDescription;
            while ((personDescription = personDescriptionFile.readLine()) != null) {
                personDescriptions.add(personDescription);
            }
            agreementFile.close();
            agreementDescriptionFile.close();
            capitalFile.close();
            cityFile.close();
            countryFile.close();
            countryDescriptionFile.close();
            eventFile.close();
            eventDescriptionFile.close();
            fullNameFile.close();
            locationFile.close();
            locationDescriptionFile.close();
            organizationFile.close();
            organizationDescriptionFile.close();
            personDescriptionFile.close();
            urlFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    private int generateRandomBetween(int start, int end){
        return start + (int) Math.round(Math.random() * (end - start));
    }
    
    private String generateRandomDate(int startYear, int endYear) {
        int month = generateRandomBetween(1,12);
        int year = generateRandomBetween(startYear, endYear);
        return month + "/" + year;
    }

    private void generatePerson(int id) {
        try {
            Person person = new Person();
            person.setId("person " + id);
            person.setLabel(fullNames.get(random.nextInt(fullNames.size())));
            person.setDescribe(personDescriptions.get(random.nextInt(personDescriptions.size())));
            person.setAge(random.nextInt(100));
            person.setGender(random.nextInt(2) == 1 ? "Male" : "Female");
            String query = "CREATE (:Person{id:{1}, name:{2}, describe:{3}, age:{4}, gender:{5}})";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, person.getId());
            preparedStatement.setString(2, person.getLabel());
            preparedStatement.setString(3, person.getDescribe());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setString(5, person.getGender());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateOrganization(int id) {
        try {
            ArrayList<String> domains = new ArrayList<String>(Arrays.asList(".com", ".com.vn", ".edu", ".co", ".ac", ".info", ".vn", ".edu.vn"));
            Organization organization = new Organization();
            organization.setId("organization " + id);
            organization.setLabel(organizations.get(random.nextInt(organizations.size())));
            organization.setDescribe(organizationDescriptions.get(random.nextInt(organizationDescriptions.size())));
            organization.setWebsite("https://www." + organization.getLabel() + domains.get(random.nextInt(domains.size())));
            organization.setHeadquarters(cities.get(random.nextInt(cities.size())));
            String query = "CREATE (:Organization{id:{1}, name:{2}, describe:{3}, headquarters:{4}, website:{5}})";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, organization.getId());
            preparedStatement.setString(2, organization.getLabel());
            preparedStatement.setString(3, organization.getDescribe());
            preparedStatement.setString(4, organization.getHeadquarters());
            preparedStatement.setString(5, organization.getWebsite());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateLocation(int id) {
        try {
            Location location = new Location();
            location.setId("location " + id);
            location.setLabel(locations.get(random.nextInt(locations.size())));
            location.setDescribe(locationDescriptions.get(random.nextInt(locationDescriptions.size())));
            location.setCity(cities.get(random.nextInt(cities.size())));
            String query = "CREATE (:Location{id:{1}, name:{2}, describe:{3}, city:{4}})";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, location.getId());
            preparedStatement.setString(2, location.getLabel());
            preparedStatement.setString(3, location.getDescribe());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateCountry(int id) {
        try {
            Country country = new Country();
            country.setId("country " + id);
            country.setLabel(countries.get(random.nextInt(countries.size())));
            country.setDescribe(countryDescriptions.get(random.nextInt(countryDescriptions.size())));
            country.setCapital(capitals.get(random.nextInt(capitals.size())));
            country.setPopulation(random.nextInt(1000000000) + 1000000);
            String query = "CREATE (:Country{id:{1}, name:{2}, describe:{3}, capital:{4}, population: {5}})";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getId());
            preparedStatement.setString(2, country.getLabel());
            preparedStatement.setString(3, country.getDescribe());
            preparedStatement.setString(4, country.getCapital());
            preparedStatement.setInt(5, country.getPopulation());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateEvent(int id) {
        try {
            Event event = new Event();
            event.setId("event " + id);
            event.setLabel(events.get(random.nextInt(events.size())));
            event.setDescribe(eventDescriptions.get(random.nextInt(eventDescriptions.size())));
            event.setVenue(locations.get(random.nextInt(locations.size())));
            String query = "CREATE (:Event{id:{1}, name:{2}, describe:{3}, venue:{4}})";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, event.getId());
            preparedStatement.setString(2, event.getLabel());
            preparedStatement.setString(3, event.getDescribe());
            preparedStatement.setString(4, event.getVenue());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateTime(int id) {
        try {
            Time time = new Time();
            time.setId("time " + id);
            time.setLabel(this.generateRandomDate(1990, 2018));
            time.setDescribe(time.getLabel());
            String query = "CREATE (:Time{id:{1}, name:{2}, describe:{3}})";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, time.getId());
            preparedStatement.setString(2, time.getLabel());
            preparedStatement.setString(3, time.getDescribe());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateAgreement(int id) {
        try {
            Agreement agreement = new Agreement();
            agreement.setId("agreement " + id);
            agreement.setLabel(agreements.get(random.nextInt(agreements.size())));
            agreement.setDescribe(agreementDescriptions.get(random.nextInt(agreementDescriptions.size())));
            String query = "CREATE (:Agreement{id:{1}, name:{2}, describe:{3}})";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, agreement.getId());
            preparedStatement.setString(2, agreement.getLabel());
            preparedStatement.setString(3, agreement.getDescribe());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateRelations(int quantity) {
        try {
            for (int i = 0; i < quantity; i++) {
                int randomCase = random.nextInt(32);
                switch (randomCase) {
                    case 0:
                        String query = "MATCH (m:Person{id:{1}}), (n:Person{id:{2}}) CREATE (m)-[:GapGo]->(n)";
                        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
                        int firstRan = 0;
                        int secondRan = 0;
                        while (firstRan == secondRan) {
                            firstRan = random.nextInt(personsNumber);
                            secondRan = random.nextInt(personsNumber);
                        }
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "person " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (n:Person{id:{1}}), (m:Person{id:{2}}) " +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "person " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 1:
                        query = "MATCH (m:Organization{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:ToChuc]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(organizationsNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "organization " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Organization{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "organization " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 2:
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:ToChuc]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 3:
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:KyThoaThuan]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = 0;
                        secondRan = 0;
                        while (firstRan == secondRan) {
                            firstRan = random.nextInt(countriesNumber);
                            secondRan = random.nextInt(countriesNumber);
                        }
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 4:
                        query = "MATCH (m:Person{id:{1}}), (n:Organization{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(organizationsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "organization " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Organization{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "organization " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 5:
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 6:
                        query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(agreementsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 7:
                        query = "MATCH (m:Organization{id:{1}}), (n:Organization{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = 0;
                        secondRan = 0;
                        while (firstRan == secondRan) {
                            firstRan = random.nextInt(organizationsNumber);
                            secondRan = random.nextInt(organizationsNumber);
                        }
                        preparedStatement.setString(1, "organization " + firstRan);
                        preparedStatement.setString(2, "organization " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Organization{id:{1}}), (n:Organization{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "organization " + firstRan);
                        preparedStatement.setString(2, "organization " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 8:
                        query = "MATCH (m:Organization{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(organizationsNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "organization " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Organization{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "organization " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 9:
                        query = "MATCH (m:Organization{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:ThamGia]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(organizationsNumber);
                        secondRan = random.nextInt(agreementsNumber);
                        preparedStatement.setString(1, "organization " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Organization{id:{1}}), (n:Agreement{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "organization " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 10:
                        query = "MATCH (m:Event{id:{1}}), (n:Location{id:{2}}) CREATE (m)-[:DienRaTai]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(eventsNumber);
                        secondRan = random.nextInt(locationsNumber);
                        preparedStatement.setString(1, "event " + firstRan);
                        preparedStatement.setString(2, "location " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Event{id:{1}}), (n:Location{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "event " + firstRan);
                        preparedStatement.setString(2, "location " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 11:
                        query = "MATCH (m:Event{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:DienRaTai]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(eventsNumber);
                        secondRan = random.nextInt(countriesNumber);
                        preparedStatement.setString(1, "event " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Event{id:{1}}), (n:Country{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "event " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 12:
                        query = "MATCH (m:Person{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:UngHo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(countriesNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Country{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 13:
                        query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:UngHo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(agreementsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 14:
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:UngHo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 15:
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:UngHo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = 0;
                        secondRan = 0;
                        while (firstRan == secondRan) {
                            firstRan = random.nextInt(countriesNumber);
                            secondRan = random.nextInt(countriesNumber);
                        }
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 16:
                        query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:UngHo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(countriesNumber);
                        secondRan = random.nextInt(agreementsNumber);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 17:
                        query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:UngHo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(countriesNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 18:
                        query = "MATCH (m:Person{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(countriesNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Country{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 19:
                        query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(agreementsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 20:
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 21:
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = 0;
                        secondRan = 0;
                        while (firstRan == secondRan) {
                            firstRan = random.nextInt(countriesNumber);
                            secondRan = random.nextInt(countriesNumber);
                        }
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 22:
                        query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(countriesNumber);
                        secondRan = random.nextInt(agreementsNumber);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 23:
                        query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:PhanDoi]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(countriesNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 24:
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:PhatBieuTai]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 25:
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:CangThangVoi]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = 0;
                        secondRan = 0;
                        while (firstRan == secondRan) {
                            firstRan = random.nextInt(countriesNumber);
                            secondRan = random.nextInt(countriesNumber);
                        }
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 26:
                        query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:HuyBo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(agreementsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Agreement{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 27:
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:HuyBo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(personsNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Person{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "person " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 28:
                        query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}}) CREATE (m)-[:HuyBo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(countriesNumber);
                        secondRan = random.nextInt(agreementsNumber);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Agreement{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "agreement " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 29:
                        query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}}) CREATE (m)-[:HuyBo]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(countriesNumber);
                        secondRan = random.nextInt(eventsNumber);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Event{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "event " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 30:
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}}) CREATE (m)-[:DamPhanVoi]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = 0;
                        secondRan = 0;
                        while (firstRan == secondRan) {
                            firstRan = random.nextInt(countriesNumber);
                            secondRan = random.nextInt(countriesNumber);
                        }
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Country{id:{1}}), (n:Country{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "country " + firstRan);
                        preparedStatement.setString(2, "country " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                    case 31:
                        query = "MATCH (m:Event{id:{1}}), (n:Time{id:{2}}) CREATE (m)-[:DienRaLuc]->(n)";
                        preparedStatement = connection.prepareStatement(query);
                        firstRan = random.nextInt(eventsNumber);
                        secondRan = random.nextInt(timesNumber);
                        preparedStatement.setString(1, "event " + firstRan);
                        preparedStatement.setString(2, "time " + secondRan);
                        preparedStatement.executeUpdate();
                        query = "MATCH (m:Event{id:{1}}), (n:Time{id:{2}})" +
                                "CREATE (o:Origin{citationDate:{3}, citationLink:{4}})" +
                                "CREATE (n)-[:TrichTu]->(o)" +
                                "CREATE (m)-[:TrichTu]->(o)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, "event " + firstRan);
                        preparedStatement.setString(2, "time " + secondRan);
                        preparedStatement.setString(3, this.generateRandomDate(2018, 2020));
                        preparedStatement.setString(4, urls.get(random.nextInt(urls.size())));
                        preparedStatement.executeUpdate();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateEntities(int quantity) {
        for (int i = 0; i < quantity; i++) {
            int ranInt = random.nextInt(7);
            switch (ranInt) {
                case 0:
                    this.generatePerson(personsNumber++);
                    break;
                case 1:
                    this.generateOrganization(organizationsNumber++);
                    break;
                case 2:
                    this.generateCountry(countriesNumber++);
                    break;
                case 3:
                    this.generateLocation(locationsNumber++);
                    break;
                case 4:
                    this.generateEvent(eventsNumber++);
                    break;
                case 5:
                    this.generateAgreement(agreementsNumber++);
                    break;
                case 6:
                    this.generateTime(timesNumber++);
                    break;
            }
        }
    }

    private void deleteCurrentData() {
        try {
            String query = "MATCH (n) DETACH DELETE n";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generate(int entitiesNumber, int relationsNumber) {
        try {
            this.deleteCurrentData();
            this.generateEntities(entitiesNumber);
            this.generateRelations(relationsNumber);
            this.connection.close();
            System.out.println("Generate random data successfully...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
