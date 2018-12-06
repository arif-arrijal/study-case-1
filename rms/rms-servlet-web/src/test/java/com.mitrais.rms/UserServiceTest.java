package com.mitrais.rms;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
    @Mock
    private UserDao userDaoMock;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testFindAllUser() {
        List<User> userList = asList(
                new User(1L,"Alan", "12345"),
                new User(2L,"John", "12345"));

        when(userDaoMock.findAll()).thenReturn(userList);

        List<User> users = userService.getAllUser();
        assertThat(users, is(notNullValue()));
        assertThat(users, is(userList));
        assertThat(users.size(), is(2));

        verify(userDaoMock, times(1)).findAll();
    }

    @Test
    public void testFindOne() {
        User user = new User(1L,"Alan", "12345");
        when(userDaoMock.find(1L)).thenReturn(Optional.of(user));

        Optional<User> testUser = userService.getById(1L);
        assertThat(testUser.orElse(null), is(notNullValue()));
        assertThat(testUser.get().getUserName(), is("Alan"));
        assertThat(testUser.get().getId(), is(1L));

        assertThat(userService.getById(2L).orElse(null), is(nullValue()));

        verify(userDaoMock, times(1)).find(1L);
        verify(userDaoMock, times(1)).find(2L);
    }

    @Test
    public void testSave() {
        User user = new User("Alan", "12345");
        when(userDaoMock.save(user)).thenReturn(true);

        assertThat(userService.saveOrUpdate(user), is(true));
        verify(userDaoMock, times(1)).save(user);
    }

    @Test(expected = RuntimeException.class)
    public void testSaveUncompleteUserData() {
        User user = new User(null, null);
        when(userDaoMock.save(user)).thenThrow(new RuntimeException());

        userService.saveOrUpdate(user);
    }

    @Test
    public void testUpdate() {
        User user = new User(1L, "Alan", "12345");
        when(userDaoMock.find(1L)).thenReturn(Optional.of(user));
        when(userDaoMock.update(user)).thenReturn(true);

        assertThat(userService.saveOrUpdate(user), is(true));
        verify(userDaoMock, times(1)).find(1L);
        verify(userDaoMock, times(1)).update(user);
    }


    @Test(expected = RuntimeException.class)
    public void testUpdateNotFoundUserId() {
        User user = new User(1L, "Alan", "12345");
        userService.saveOrUpdate(user);
    }
}
