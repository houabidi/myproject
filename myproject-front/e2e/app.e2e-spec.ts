import { MyprojectFrontPage } from './app.po';

describe('myproject-front App', () => {
  let page: MyprojectFrontPage;

  beforeEach(() => {
    page = new MyprojectFrontPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
