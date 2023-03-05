# 國學院大學相撲部ホームページ

 [http://kokugakuinsumo.com/](http://kokugakuinsumo.com/)

## 開発手順

下記を実行後、任意のclj/cljsファイルを修正して下さい。

```sh
$ lein figwheel
$ lein garden auto
```

* view

`src/main/cljs/kokugakuinsumo/views/*.cljs`

* style

`src/main/clj/kokugakuinsumo/styles/*.clj`

## Deploy

Uses peaceiris/actions-gh-pages@v2.5.1 in Github actions.

If it doesn't work, In source branch

```
lein do clean, garden once, cljsbuild once min
```

Then copy all files under ./resources/public to master branch and push.