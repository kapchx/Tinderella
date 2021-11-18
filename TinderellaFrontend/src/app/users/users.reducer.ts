import { initialState } from './users.state';

import { LOAD_PROFILE, USER_LOGGED_IN, USER_LOGOUT, USER_PROFILE, USER_REGISTERED, USERS_LIST } from './users.actions';

function userLogin(state, action) {
    const result = action.result;

    if (result.success) {
        return Object.assign({}, state, {
            userAuthenticated: result.success,
            token: result.token,
            username: result.user.email
        });
    }

    return state;
}

function userRegistration(state, action) {
    const result = action.result;
    return Object.assign({}, state, {
        userRegistered: result.success
    });
}

function logout(state, action) {
    return Object.assign({}, state, {
        userAuthenticated: false,
        token: null,
        username: null
    });
}

function loadProfile(state, profile) {
    return Object.assign({}, state, { profile });
}

function loadUsers(state, usersList) {
    return Object.assign({}, state, { usersList });
}

export function usersReducer(state = initialState, action) {
    switch (action.type) {
        case USER_REGISTERED:
            return userRegistration(state, action);
        case USER_LOGGED_IN:
            return userLogin(state, action);
        case USER_LOGOUT:
            return logout(state, action);
        case USERS_LIST:
            return loadUsers(state, action.users);
        case LOAD_PROFILE:
            return loadProfile(state, action.profile);
        default:
            return state;
    }
}
