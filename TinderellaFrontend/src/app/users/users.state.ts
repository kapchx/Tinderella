import { ProfileModel } from '../../users/profile/profile-user.model';
export interface IUsersState {
    userRegistered: boolean;
    userAuthenticated: boolean;
    token: string;
    username: string;
    usersList: Array<ProfileModel>;
    profile: ProfileModel;
}

export const initialState: IUsersState = {
    userRegistered: false,
    userAuthenticated: false,
    token: null,
    username: null,
    usersList: [],
    profile: null
};
