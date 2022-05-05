package edu.java8.practice.service.impl;


import edu.java8.practice.domain.Privilege;
import edu.java8.practice.domain.User;
import edu.java8.practice.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class UserServiceImplTest {

    private UserService userService = new UserServiceImpl();

    private static final List<Privilege> ALL_PRIVILEGES = asList(Privilege.values());

    @Test
    public void shouldSortUsersByAgeDescAndNameDesc() {
        final User user1 = new User(1L, "John", "Doe", 26, ALL_PRIVILEGES);
        final User user2 = new User(2L, "Greg", "Smith", 30, ALL_PRIVILEGES);
        final User user3 = new User(3L, "Alex", "Smith", 13, ALL_PRIVILEGES);
        final User user4 = new User(4L, "Dynamic", "Blunt", 30,
                Collections.singletonList(Privilege.READ));
        final User user5 = new User(5L, "Dynamic", "BluntTech", 30,
                ALL_PRIVILEGES);
        final List<User> sortedUsers =
                userService.sortByAgeDescAndNameAsc(asList(user1, user2, user3,user4, user5));

        System.out.println(sortedUsers);
        assertThat(sortedUsers).containsExactly(user4, user5, user2, user1, user3);

        Assertions.assertArrayEquals(
                sortedUsers.toArray(),
                new User[]{ user4, user5, user2, user1, user3}
        );
    }

    @Test
    @DisplayName("Return all distinct privilege in the list")
    public void shouldReturnDistinctPrivilegesForUsers() {
        final User createUser = new User(1L, "John", "Doe", 26,
                Collections.singletonList(Privilege.CREATE));
        final User updateUser = new User(2L, "Greg", "Smith", 30,
                Collections.singletonList(Privilege.UPDATE));
        final User updateUser1 = new User(3L, "Greg", "Smith", 20,
                Collections.singletonList(Privilege.UPDATE));
        final User deleteUser = new User(4L, "Alex", "Smith", 13,
                Collections.singletonList(Privilege.DELETE));
        final List<Privilege> distinctPrivileges =
                userService.getAllDistinctPrivileges(asList(
                        createUser,
                        updateUser,
                        updateUser1,
                        deleteUser));

        assertThat(distinctPrivileges).containsExactlyInAnyOrder(Privilege.CREATE, Privilege.UPDATE, Privilege.DELETE);

        Assertions.assertEquals(distinctPrivileges.size(), 3);

    }

    @Test
    public void shouldReturnUpdateUserWithAgeHigherThanGiven() {
        final User updateUser1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User updateUser2 = new User(2L, "Greg", "Smith", 30, singletonList(Privilege.UPDATE));
        final User deleteUser = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Optional<User> foundUser =
                userService.getUpdateUserWithAgeHigherThan(asList(updateUser1, updateUser2, deleteUser), 29);

        assertThat(foundUser).hasValueSatisfying(user ->
                assertThat(user).isEqualTo(updateUser2));
    }

    @Test
    @DisplayName("Count of Users for each Privilege")
    public void shouldReturnGroupedMapByNumberOfPrivileges() {
        final int ONE_PRIVILEGE = 1;
        final int TWO_PRIVILEGES = 2;
        final int FOUR_PRIVILEGES = 4;

        final User userWith2Privileges = new User(1L, "John", "Doe", 26, asList(Privilege.UPDATE, Privilege.CREATE));
        final User userWith4Privileges = new User(2L, "Greg", "Smith", 30, ALL_PRIVILEGES);
        final User userWith1Privileges1 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));
        final User userWith1Privileges2 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Map<Integer, List<User>> groupedMap =
                userService.groupByCountOfPrivileges(asList(userWith2Privileges, userWith4Privileges, userWith1Privileges1, userWith1Privileges1));

        assertThat(groupedMap)
                .containsExactly(
                        entry(ONE_PRIVILEGE, asList(userWith1Privileges1, userWith1Privileges2)),
                        entry(TWO_PRIVILEGES, singletonList(userWith2Privileges)),
                        entry(FOUR_PRIVILEGES, singletonList(userWith4Privileges)));
    }

    @Test
    public void shouldReturnAverageAgeForUsers() {
        final int expectedAverage = 23;

        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Smith", 30, singletonList(Privilege.UPDATE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final double averageAge = userService.getAverageAgeForUsers(asList(user1, user2, user3));

        assertThat(averageAge).isEqualTo(expectedAverage);
    }

    @Test
    @DisplayName("Return average age of Users")
    public void shouldReturnMinusOneInsteadOfAverageForEmptyList() {
        final User createUser = new User(1L, "John", "Doe", 26,
                Collections.singletonList(Privilege.CREATE));
        final User updateUser = new User(2L, "Greg", "Smith", 30,
                Collections.singletonList(Privilege.UPDATE));
        final User updateUser1 = new User(3L, "Greg", "Smith", 20,
                Collections.singletonList(Privilege.UPDATE));
        final User deleteUser = new User(4L, "Alex", "Smith", 13,
                Collections.singletonList(Privilege.DELETE));

        double averageAge = userService.getAverageAgeForUsers(
                Arrays.asList(createUser, updateUser, updateUser1, deleteUser)
        );

        System.out.println(averageAge);
        Assertions.assertEquals(averageAge, 22.25);
            }

    @Test
    public void shouldReturnMostFrequentLastName() {
        final String mostFrequentName = "Smith";

        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", mostFrequentName, 30, singletonList(Privilege.UPDATE));
        final User user3 = new User(3L, "Alex", mostFrequentName, 13, singletonList(Privilege.DELETE));

        final Optional<String> mostFrequentLastName =
                userService.getMostFrequentLastName(asList(user1, user2, user3));

        assertThat(mostFrequentLastName)
                .hasValueSatisfying(name -> assertThat(name).isEqualToIgnoringCase(mostFrequentName));
    }

    @Test
    @DisplayName("Return most frequent occurring last name from the list")
    public void shouldReturnOptionalEmptyIfThereAreDistinctNumberOfLastNames() {
        final User createUser = new User(1L, "John", "Doe", 26,
                Collections.singletonList(Privilege.CREATE));
        final User updateUser = new User(2L, "Greg", "Smith", 30,
                Collections.singletonList(Privilege.UPDATE));
        final User updateUser1 = new User(3L, "Greg", "Smith", 20,
                Collections.singletonList(Privilege.UPDATE));
        final User deleteUser = new User(4L, "Alex", "Smith", 13,
                Collections.singletonList(Privilege.DELETE));

        String lastName = userService.getMostFrequentLastName(
                Arrays.asList(createUser, updateUser, updateUser1, deleteUser)
        ).get();

        System.out.println(lastName);
        Assertions.assertEquals(lastName, "Smith");
    }

    @Test
    public void shouldFilterListOfUsersByGivenConditions() {
        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Predicate<User> firstNameLongerThan3 = u -> u.getFirstName().length() > 3;
        final Predicate<User> numberOfPrivilegesBiggerThan2 = u -> u.getPrivileges().size() > 2;

        final List<User> filteredUsers =
                userService.filterBy(asList(user1, user2, user3), firstNameLongerThan3, numberOfPrivilegesBiggerThan2);

        assertThat(filteredUsers).containsExactly(user2);
    }

    @Test
    public void shouldReturnTheSameListIfNoPredicatesProvided() {
        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final List<User> users = asList(user1, user2, user3);

        final List<User> filteredUsers =
                userService.filterBy(users);

        assertThat(filteredUsers).isEqualTo(users);
    }

    @Test
    public void shouldConvertListOfUsersToCSVOfUsersLastNames() {
        final User user1 = new User(1L, "John", "Doe", 26, emptyList());
        final User user2 = new User(2L, "Greg", "Jonson", 30, emptyList());
        final User user3 = new User(3L, "Alex", "Smith", 13, emptyList());

        final String csv = userService.convertTo(asList(user1, user2, user3), "|", User::getLastName);

        assertThat(csv).isEqualTo("Doe|Jonson|Smith");
    }

    @Test
    public void shouldReturnGroupedMapByPrivileges() {
        final User user1 = new User(1L, "John", "Doe", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Map<Privilege, List<User>> groupedMap = userService.groupByPrivileges(asList(user1, user2, user3));

        assertThat(groupedMap)
                .contains(
                        entry(Privilege.UPDATE, asList(user1, user2)),
                        entry(Privilege.CREATE, singletonList(user2)),
                        entry(Privilege.DELETE, asList(user2, user3))
                );
    }

    @Test
    public void shouldReturnGroupedMapOfLastNamesAndEncounteredNumber() {
        final User user1 = new User(1L, "John", "Smith", 26, singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, singletonList(Privilege.DELETE));

        final Map<String, Long> numberOfLastNames = userService.getNumberOfLastNames(asList(user1, user2, user3));

        assertThat(numberOfLastNames)
                .contains(entry("Smith", 2L), entry("Jonson", 1L));
    }

}