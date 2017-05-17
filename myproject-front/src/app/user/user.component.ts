import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/user.service";

@Component({
  selector: 'myproject-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  private user: any = {};

  constructor(private _userService: UserService) { }

  ngOnInit() {

    /**
     * on initialise notre utilisateur par le 1er dans la liste
     */
    this._userService.findAll()
      .subscribe(users => {
        this.user = users[0]
      });
  }

}
