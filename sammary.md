# Todoアプリまとめ
## 殴り書きになってしまっているため、少しずつまとめていきます。

SlickDBActionProviderを継承したときに使えるメソッド (get,add,updateなど)
メソッドを定義する際、RunDBActionの引数に、Tableを渡さなければならない。(定義する必要がある)
package lib.persistence.dbファイルで生成したケースクラスから、SlickDBActionProviderファイル内にオブジェクトを作成する。(extendsで継承する)
そのオブジェクトをlazy valで定義。(lazy valを使うことで、呼ばれた時に初めてインスタンスができるので、初期の段階で呼ばれずメモリ的に遅くならない。

package lib.modelは、WithNoIdとEmbeddedIdの型の定義をしたり、EnumStatusを用いて、抽象クラスの作成などをする。

package lib.persistanceはslickResourceProviderを継承するとメソッドが使えるようになり、定義がされている。
package lib.persistance.dbは、上記lib.persistanceで定義されたメソッドのRunDBActionの引数で必要な引数を定義している。
(ケースクラスの生成 -> SlickResourceProviderファイルでオブジェクト生成 -> テーブル定義)
