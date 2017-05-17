import { RouterModule, Routes } from '@angular/router';

// APP COMPONENTS

import {UserComponent} from "./user/user.component";


const ROUTES: Routes = [
  {path: 'user', component: UserComponent}
];

export const APP_ROUTES = RouterModule.forRoot(ROUTES,{useHash: true});
