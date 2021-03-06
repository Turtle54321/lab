/**
 * postmate - A powerful, simple, promise-based postMessage library
 * @version 1.1.9
 * @link https://github.com/dollarshaveclub/postmate
 * @author Jacob Kelley <jakie8@gmail.com>
 * @license MIT */
!function (e, t) {
    e.Postmate = t()
}(this, function () {
    "use strict";
    function e(e, t) {
        if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
    }

    function t() {
        return ++h
    }

    function n() {
        var e;
        m.debug && (e = console).log.apply(e, arguments)
    }

    //修复IE11下的问题
    function i(e) {
        var t = document.createElement("a");
        t.href = e;
        var protocol = t.protocol.length > 4 ? t.protocol : window.location.protocol;
        var host = t.host.length ? ((t.port === '80' || t.port === '443') ? t.hostname : t.host) : window.location.host;
        return t.origin || protocol + "//" + host;
    }

    function a(e, t) {
        return e.origin === t && ("object" === s(e.data) && ("postmate" in e.data && (e.data.type === d && !!{
                "handshake-reply": 1,
                call: 1,
                emit: 1,
                reply: 1,
                request: 1
            }[e.data.postmate])))
    }

    function r(e, t) {
        var n = "function" == typeof e[t] ? e[t]() : e[t];
        return m.Promise.resolve(n)
    }

    var o = function () {
        function e(e, t) {
            for (var n = 0; n < t.length; n++) {
                var i = t[n];
                i.enumerable = i.enumerable || !1, i.configurable = !0, "value" in i && (i.writable = !0), Object.defineProperty(e, i.key, i)
            }
        }

        return function (t, n, i) {
            return n && e(t.prototype, n), i && e(t, i), t
        }
    }(), s = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
            return typeof e
        } : function (e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        }, d = "application/x-postmate-v1+json", l = Object.prototype.hasOwnProperty, h = 0, c = function () {
        function i(t) {
            var a = this;
            e(this, i), this.parent = t.parent, this.frame = t.frame, this.child = t.child, this.childOrigin = t.childOrigin, this.events = {}, n("Parent: Registering API"), n("Parent: Awaiting messages..."), this.listener = function (e) {
                var t = ((e || {}).data || {}).value || {}, i = t.data, r = t.name;
                "emit" === e.data.postmate && (n("Parent: Received event emission: " + r), r in a.events && a.events[r].call(a, i))
            }, this.parent.addEventListener("message", this.listener, !1), n("Parent: Awaiting event emissions from Child")
        }

        return o(i, [{
            key: "get", value: function (e) {
                var n = this;
                return new m.Promise(function (i) {
                    var a = t(), r = function e(t) {
                        t.data.uid === a && "reply" === t.data.postmate && (n.parent.removeEventListener("message", e, !1), i(t.data.value))
                    };
                    n.parent.addEventListener("message", r, !1), n.child.postMessage({
                        postmate: "request",
                        type: d,
                        property: e,
                        uid: a
                    }, n.childOrigin)
                })
            }
        }, {
            key: "call", value: function (e, t) {
                this.child.postMessage({postmate: "call", type: d, property: e, data: t}, this.childOrigin)
            }
        }, {
            key: "on", value: function (e, t) {
                this.events[e] = t
            }
        }, {
            key: "destroy", value: function () {
                n("Parent: Destroying Postmate instance"), window.removeEventListener("message", this.listener, !1), this.frame.parentNode.removeChild(this.frame)
            }
        }]), i
    }(), u = function () {
        function t(i) {
            var o = this;
            e(this, t), this.model = i.model, this.parent = i.parent, this.parentOrigin = i.parentOrigin, this.child = i.child, n("Child: Registering API"), n("Child: Awaiting messages..."), this.child.addEventListener("message", function (e) {
                if (a(e, o.parentOrigin)) {
                    n("Child: Received request", e.data);
                    var t = e.data, i = t.property, s = t.uid, l = t.data;
                    if ("call" === e.data.postmate)return void(i in o.model && "function" == typeof o.model[i] && o.model[i].call(o, l));
                    r(o.model, i).then(function (t) {
                        return e.source.postMessage({
                            property: i,
                            postmate: "reply",
                            type: d,
                            uid: s,
                            value: t
                        }, e.origin)
                    })
                }
            })
        }

        return o(t, [{
            key: "emit", value: function (e, t) {
                n('Child: Emitting Event "' + e + '"', t), this.parent.postMessage({
                    postmate: "emit",
                    type: d,
                    value: {name: e, data: t}
                }, this.parentOrigin)
            }
        }]), t
    }(), m = function () {
        function t(n) {
            e(this, t);
            var i = n.container, a = void 0 === i ? void 0 !== a ? a : document.body : i, r = n.model, o = n.url;
            return this.parent = window, this.frame = document.createElement("iframe"), a.appendChild(this.frame), this.child = this.frame.contentWindow || this.frame.contentDocument.parentWindow, this.model = r || {}, this.sendHandshake(o)
        }

        return o(t, [{
            key: "sendHandshake", value: function (e) {
                var r = this, o = i(e), s = 0, l = void 0;
                return new t.Promise(function (t, i) {
                    var h = function e(s) {
                        return !!a(s, o) && ("handshake-reply" === s.data.postmate ? (clearInterval(l), n("Parent: Received handshake reply from Child"), r.parent.removeEventListener("message", e, !1), r.childOrigin = s.origin, n("Parent: Saving Child origin", r.childOrigin), t(new c(r))) : (n("Parent: Invalid handshake reply"), i("Failed handshake")))
                    };
                    r.parent.addEventListener("message", h, !1);
                    var u = function () {
                        s++, n("Parent: Sending handshake attempt " + s, {childOrigin: o}), r.child.postMessage({
                            postmate: "handshake",
                            type: d,
                            model: r.model
                        }, o), 5 === s && clearInterval(l);
                        if (s === 5) {
                            if (window.postmateError && typeof window.postmateError == "function") {
                                window.postmateError()
                            }
                        }
                    }, m = function () {
                        u(), l = setInterval(u, 500)
                    };
                    r.frame.attachEvent ? r.frame.attachEvent("onload", m) : r.frame.onload = m, n("Parent: Loading frame", {url: e}), r.frame.src = e
                })
            }
        }]), t
    }();
    return m.debug = !1, m.Promise = function () {
        try {
            return window ? window.Promise : Promise
        } catch (e) {
            return null
        }
    }(), m.Model = function () {
        function t(n) {
            return e(this, t), this.child = window, this.model = n, this.parent = this.child.parent, this.sendHandshakeReply()
        }

        return o(t, [{
            key: "sendHandshakeReply", value: function () {
                var e = this;
                return new m.Promise(function (t, i) {
                    var a = function a(r) {
                        if (r.data.postmate) {
                            if ("handshake" === r.data.postmate) {
                                n("Child: Received handshake from Parent"), e.child.removeEventListener("message", a, !1), n("Child: Sending handshake reply to Parent"), r.source.postMessage({
                                    postmate: "handshake-reply",
                                    type: d
                                }, r.origin), e.parentOrigin = r.origin;
                                var o = r.data.model;
                                if (o) {
                                    for (var s = Object.keys(o), h = 0; h < s.length; h++)l.call(o, s[h]) && (e.model[s[h]] = o[s[h]]);
                                    n("Child: Inherited and extended model from Parent")
                                }
                                return n("Child: Saving Parent origin", e.parentOrigin), t(new u(e))
                            }
                            return i("Handshake Reply Failed")
                        }
                    };
                    e.child.addEventListener("message", a, !1)
                })
            }
        }]), t
    }(), m
});
